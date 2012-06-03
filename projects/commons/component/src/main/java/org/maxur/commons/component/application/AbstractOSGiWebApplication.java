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
import org.maxur.commons.core.api.Refresher;
import org.maxur.commons.osgi.MutableInjectorHolder;
import org.maxur.commons.view.api.OSGiWebApplication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Maxim Yunusov
 * @version 1.0 10.05.12
 */
public abstract class AbstractOSGiWebApplication extends WebApplication implements OSGiWebApplication {

    private OsgiClassResolver classResolver;

    private final Refresher<Injector> refresher;

    public AbstractOSGiWebApplication(final String pid) {
        refresher = MutableInjectorHolder.freshnessController(pid);
        refresher.get().injectMembers(this);
    }

    @Inject
    public void updateInjector(Injector injector) {
        setMetaData(GuiceInjectorHolder.INJECTOR_KEY, new GuiceInjectorHolder(injector));
    }

    @Override
    public void registersResource(final Object object) {
        this.classResolver.addClassLoader(object.getClass().getClassLoader());
    }

    /** {@inheritDoc} */
    @Override
    public WebRequest newWebRequest(HttpServletRequest servletRequest, String filterPath) {
        if (refresher.isStale()) {
            final Injector injector = refresher.get();
            injector.injectMembers(this);

        }
        return super.newWebRequest(servletRequest, filterPath);
    }

    /**
     * It's template method. It can not be overrides.
     * One can use 'doInit' for override purpose.
     * {@inheritDoc}
     */
    @Override
    protected final void init() {
        initInjector();
        initResolver();
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

    private void initInjector() {
        final Injector injector = refresher.get();
        getComponentInstantiationListeners().add(injector != null ?
                new GuiceComponentInjector(this, injector) :
                new GuiceComponentInjector(this));
    }

    private void initResolver() {
        this.classResolver = new OsgiClassResolver();
        this.classResolver.addClassLoader(this.getClass().getClassLoader());
        getApplicationSettings().setClassResolver(classResolver);
    }

    protected abstract void doInit();

}
