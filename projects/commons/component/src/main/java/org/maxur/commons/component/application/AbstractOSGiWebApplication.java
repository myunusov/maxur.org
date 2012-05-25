package org.maxur.commons.component.application;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.guice.GuiceInjectorHolder;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.http.WebRequest;
import org.maxur.commons.component.application.classresolver.OsgiClassResolver;
import org.maxur.commons.osgi.GuiceModuleHolder;
import org.maxur.commons.osgi.OSGiObserver;
import org.maxur.commons.view.api.OSGiWebApplication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Maxim Yunusov
 * @version 1.0 10.05.12
 */
public abstract class AbstractOSGiWebApplication extends WebApplication
        implements OSGiWebApplication, OSGiObserver {

    private OsgiClassResolver classResolver;

    private final String pid;

    @Inject
    private Injector injector;

    private boolean isStale;

    public AbstractOSGiWebApplication(final String pid) {
        this.pid = pid;
        GuiceModuleHolder.inject(pid, this);
        GuiceModuleHolder.addObserver(pid, this);
    }

    @Override
    public void registersResource(final Object object) {
        this.classResolver.addClassLoader(object.getClass().getClassLoader());
    }

    /** {@inheritDoc} */
    @Override
    public WebRequest newWebRequest(HttpServletRequest servletRequest, String filterPath) {
        if (this.isStale) {
            GuiceModuleHolder.inject(pid, this);
            setMetaData(GuiceInjectorHolder.INJECTOR_KEY, new GuiceInjectorHolder(injector));
        }
        return super.newWebRequest(servletRequest, filterPath);
    }

    /**
     * It's template method. It can not be overrides.
     * One can use 'doInit' for override purpose.
     *
     * {@inheritDoc}
     *
     */
    @Override
    protected final void init() {
        getComponentInstantiationListeners().add(createInjector());
        this.classResolver = new OsgiClassResolver();
        this.classResolver.addClassLoader(this.getClass().getClassLoader());
        getApplicationSettings().setClassResolver(classResolver);
        doInit();
    }

    private GuiceComponentInjector createInjector() {
        return injector != null ?
                new GuiceComponentInjector(this, injector) :
                new GuiceComponentInjector(this);
    }

    @Override
    public void update() {
        this.isStale = true;
    }

    protected abstract void doInit();

}
