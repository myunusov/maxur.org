package org.maxur.commons.view;

import com.google.inject.Injector;
import org.apache.wicket.Session;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.guice.GuiceInjectorHolder;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.http.WebRequest;
import org.maxur.commons.view.pages.about.AboutPage;
import org.maxur.commons.view.pages.home.HomePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>MaxurApplication class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 27.09.11
 */
public class MaxurApplication extends WebApplication {

    private static final String CURRENT_ENCODING = "UTF-8";

    private static Injector injector;

    /**
     * <p>Setter for the field <code>injector</code>.</p>
     *
     * @param injector a {@link com.google.inject.Injector} object.
     */
    @Inject
    public static void setInjector(Injector injector) {
        MaxurApplication.injector = injector;
    }

    private String version;


    /**
     * <p>Setter for the field <code>version</code>.</p>
     *
     * @param version a {@link java.lang.String} object.
     */
    @Inject
    public void setVersion(@Named("version") String version) {
        this.version = version;
    }

    /** {@inheritDoc} */
    @Override
    public final Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    /** {@inheritDoc} */
    @Override
    protected final void init() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Version : " + version);

        getMarkupSettings().setDefaultMarkupEncoding(CURRENT_ENCODING);
        getRequestCycleSettings().setResponseRequestEncoding(CURRENT_ENCODING);
        getComponentInstantiationListeners().add(createInjector());
        mountPage("/home", HomePage.class);
        mountPage("/about", AboutPage.class);
    }

    private GuiceComponentInjector createInjector() {
        return injector != null ? new GuiceComponentInjector(this, injector) : new GuiceComponentInjector(this);
    }


    /** {@inheritDoc} */
    @Override
    public WebRequest newWebRequest(HttpServletRequest servletRequest, String filterPath) {
        setMetaData(GuiceInjectorHolder.INJECTOR_KEY, new GuiceInjectorHolder(injector));
        return super.newWebRequest(servletRequest, filterPath);
    }

    /** {@inheritDoc} */
    @Override
    public final Session newSession(final Request request, final Response response) {
        return new MaxurSession(request);
    }
}
