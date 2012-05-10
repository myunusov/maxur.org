package org.maxur.commons.component.application;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.guice.GuiceInjectorHolder;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.http.WebRequest;
import org.maxur.commons.component.application.classresolver.OsgiClassResolver;
import org.maxur.commons.view.api.OSGiWebApplication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Maxim Yunusov
 * @version 1.0 10.05.12
 */
public abstract class AbstractOSGiWebApplication extends WebApplication implements OSGiWebApplication {

    private static Injector injector;

    private OsgiClassResolver classResolver;

    /**
     * <p>Setter for the field <code>injector</code>.</p>
     *
     * @param injector a {@link com.google.inject.Injector} object.
     */
    @Inject
    public static void setInjector(Injector injector) {
        AbstractOSGiWebApplication.injector = injector;
    }

    @Override
    public void registersResource(final Object object) {
        this.classResolver.addClassLoader(object.getClass().getClassLoader());
    }

    /** {@inheritDoc} */
    @Override
    public WebRequest newWebRequest(HttpServletRequest servletRequest, String filterPath) {
        setMetaData(GuiceInjectorHolder.INJECTOR_KEY, new GuiceInjectorHolder(injector));
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
        if (injector != null) {
            injector.injectMembers(this);
        }
        getComponentInstantiationListeners().add(createInjector());

        this.classResolver = new OsgiClassResolver();
        this.classResolver.addClassLoader(this.getClass().getClassLoader());
        getApplicationSettings().setClassResolver(classResolver);

        doInit();
    }

    private GuiceComponentInjector createInjector() {
        return injector != null ? new GuiceComponentInjector(this, injector) : new GuiceComponentInjector(this);
    }

    protected abstract void doInit();

}
