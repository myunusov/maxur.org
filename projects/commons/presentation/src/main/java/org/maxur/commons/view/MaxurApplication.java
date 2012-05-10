package org.maxur.commons.view;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.wicket.Session;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.maxur.commons.component.application.AbstractOSGiWebApplication;
import org.maxur.commons.component.model.bookmark.Bookmark;
import org.maxur.commons.component.model.bookmark.Bookmarks;
import org.maxur.commons.component.model.webclient.WebBrowser;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.view.api.MenuItems;
import org.maxur.commons.view.api.StyleBehavior;
import org.maxur.commons.view.api.PageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>MaxurApplication class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 27.09.11
 */
public class MaxurApplication extends AbstractOSGiWebApplication {

    private static final String CURRENT_ENCODING = "UTF-8";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    @Named("version")
    private String version;

    @Inject
    private MenuItems menuItems;

    @Inject
    private Bookmarks bookmarks;

    @Inject @Named("HomePage")
    private PageProvider homePageProvider;

    @Inject
    private WebBrowserDetector detector;

    @Inject
    private StyleBehavior styleBehavior;

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
        return homePageProvider.get();
    }

    @Override
    protected void doInit() {
        getMarkupSettings().setDefaultMarkupEncoding(CURRENT_ENCODING);
        getRequestCycleSettings().setResponseRequestEncoding(CURRENT_ENCODING);
        for (Bookmark bookmark : bookmarks) {
            mountPage(bookmark.getShortLink(), bookmark.getTargetClass());
        }
        logger.debug(String.format("Star Web Application Maxur (Version : %s)", version));
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

    /**
     * Returns Style behavior gets it from bundle context.
     *
     * Prevents exception on serialization Peaberry Proxy with inject directly to Page.
     *
     * @return The Style Behavior.
     */
    public Behavior getStyleBehavior() {
        return styleBehavior.asBehavior();
    }
}
