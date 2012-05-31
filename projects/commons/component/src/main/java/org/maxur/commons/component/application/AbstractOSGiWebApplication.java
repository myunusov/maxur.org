package org.maxur.commons.component.application;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.apache.wicket.Application;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.guice.GuiceInjectorHolder;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.http.WebRequest;
import org.maxur.commons.component.application.classresolver.OsgiClassResolver;
import org.maxur.commons.domain.Observer;
import org.maxur.commons.osgi.MutableInjectorHolder;
import org.maxur.commons.view.api.OSGiWebApplication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Maxim Yunusov
 * @version 1.0 10.05.12
 */
public abstract class AbstractOSGiWebApplication extends WebApplication
        implements OSGiWebApplication, Observer {

    private OsgiClassResolver classResolver;

    private final String pid;

    @Inject
    private Injector injector;

    private boolean isStale;

    public AbstractOSGiWebApplication(final String pid) {
        this.pid = pid;
        MutableInjectorHolder.get(pid).inject(this);
        MutableInjectorHolder.get(pid).addObserver(this);
    }

    @Override
    public void registersResource(final Object object) {
        this.classResolver.addClassLoader(object.getClass().getClassLoader());
    }

    /** {@inheritDoc} */
    @Override
    public WebRequest newWebRequest(HttpServletRequest servletRequest, String filterPath) {
        if (this.isStale) {
            MutableInjectorHolder.get(pid).inject(this);
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
        createInjector();
        createResolver();
        initGuarder();
        doInit();
    }

    private void initGuarder() {
        final SecurePackageResourceGuard guard = (SecurePackageResourceGuard) Application.get()
                .getResourceSettings()
                .getPackageResourceGuard();
        guard.addPattern("+*.woff");
        guard.addPattern("+*.ttf");
    }

    private void createInjector() {
        getComponentInstantiationListeners().add(injector != null ?
                new GuiceComponentInjector(this, injector) :
                new GuiceComponentInjector(this));
    }

    private void createResolver() {
        this.classResolver = new OsgiClassResolver();
        this.classResolver.addClassLoader(this.getClass().getClassLoader());
        getApplicationSettings().setClassResolver(classResolver);
    }

    @Override
    public void update() {
        this.isStale = true;
    }

    protected abstract void doInit();

}
