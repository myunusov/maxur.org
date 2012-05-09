package org.maxur.commons.view;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import org.apache.wicket.Session;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.guice.GuiceInjectorHolder;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.maxur.commons.component.model.webclient.WebBrowser;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.view.api.OSGiWebApplication;
import org.maxur.commons.view.api.StyleBehavior;
import org.maxur.commons.view.pages.about.AboutPage;
import org.maxur.commons.view.pages.home.HomePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>MaxurApplication class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 27.09.11
 */
public class MaxurApplication extends WebApplication implements OSGiWebApplication {

    private static final String CURRENT_ENCODING = "UTF-8";

    private static Injector injector;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    @Named("version")
    private String version;

    @Inject
    private WebBrowserDetector detector;

    @Inject
    private StyleBehavior styleBehavior;

    private OsgiClassResolver classResolver;

    /**
     * <p>Setter for the field <code>injector</code>.</p>
     *
     * @param injector a {@link com.google.inject.Injector} object.
     */
    @Inject
    public static void setInjector(Injector injector) {
        MaxurApplication.injector = injector;
    }

    /**
     * <p>Setter for the field <code>version</code>.</p>
     *
     * @param version a {@link java.lang.String} object.
     */
    @SuppressWarnings("UnusedDeclaration")
    public void setVersion(String version) {
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
        getMarkupSettings().setDefaultMarkupEncoding(CURRENT_ENCODING);
        getRequestCycleSettings().setResponseRequestEncoding(CURRENT_ENCODING);
        if (injector != null) {
            injector.injectMembers(this);
        }
        logger.debug(String.format("Star Web Application Maxur (Version : %s)", version));
        getComponentInstantiationListeners().add(createInjector());
        mountPage("/home", HomePage.class);
        mountPage("/about", AboutPage.class);

        this.classResolver = new OsgiClassResolver();
        this.classResolver.addClassLoader(this.getClass().getClassLoader());
        getApplicationSettings().setClassResolver(classResolver);
    }

    private GuiceComponentInjector createInjector() {
        return injector != null ? new GuiceComponentInjector(this, injector) : new GuiceComponentInjector(this);
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

    /** {@inheritDoc} */
    @Override
    public final Session newSession(final Request request, final Response response) {
        if (detector != null) {
            final WebBrowser browser = detector.detect(getHttpServletRequest());
            logger.debug(String.format(
                    "New Session with Web Client on %s (%s)",
                    browser.getBrowserType(), browser.getVersion())
            );
        }
        return new MaxurSession(request);
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest());
    }

    public Behavior getStyleBehavior() {
        return styleBehavior.asBehavior();
    }
}
