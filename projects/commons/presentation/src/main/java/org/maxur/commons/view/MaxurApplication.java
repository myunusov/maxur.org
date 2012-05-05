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
 * @author Maxim Yunusov
 * @version 1.0 27.09.11
 */
public class MaxurApplication extends WebApplication {

    private static final String CURRENT_ENCODING = "UTF-8";

    private static Injector injector;

    @Inject
    public static void setInjector(Injector injector) {
        MaxurApplication.injector = injector;
    }

    private String version;


    @Inject
    public void setVersion(@Named("version") String version) {
        this.version = version;
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public final Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
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


    @Override
    public WebRequest newWebRequest(HttpServletRequest servletRequest, String filterPath) {
        setMetaData(GuiceInjectorHolder.INJECTOR_KEY, new GuiceInjectorHolder(injector));
        return super.newWebRequest(servletRequest, filterPath);
    }

    /**
     * @see org.apache.wicket.Application#newSession(org.apache.wicket.request.Request,
     *      org.apache.wicket.request.Response)
     */
    @Override
    public final Session newSession(final Request request, final Response response) {
        return new MaxurSession(request);
    }
}