package org.maxur.commons.view;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.settings.IExceptionSettings;
import org.maxur.commons.component.application.AbstractOSGiWebApplication;
import org.maxur.commons.component.behavior.ThemeBehavior;
import org.maxur.commons.component.model.bookmark.Bookmark;
import org.maxur.commons.component.model.bookmark.Bookmarks;
import org.maxur.commons.component.model.webclient.WebBrowser;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.view.api.MenuItems;
import org.maxur.commons.view.api.PageProvider;
import org.maxur.commons.view.api.StyleBehavior;
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

    private static final String DEFAULT_ENCODING = "UTF-8";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    @Named("version")
    private String version;

    @Inject
    private MenuItems menuItems;

    @Inject
    private Bookmarks bookmarks;

    @Inject
    @Named("HomePage")
    private PageProvider homePageProvider;

    @Inject
    @Named("InternalErrorPage")
    private PageProvider internalErrorProvider;

    @Inject
    @Named("ExpiredPage")
    private PageProvider expiredPageProvider;

    @Inject
    @Named("AccessDeniedPage")
    private PageProvider accessDeniedPageProvider;

    @Inject
    private WebBrowserDetector detector;

    @Inject
    private ThemeBehavior themeBehavior;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public final Class<? extends WebPage> getHomePage() {
        return homePageProvider.get();
    }

    @Override
    protected void doInit() {
        setEncoding();
        setMode();
        setBookmarks();
        setErrorPages();
        logger.debug(String.format("Star Web Application Maxur (Version : %s)", version));
    }

    private void setErrorPages() {
        getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
        getApplicationSettings().setInternalErrorPage(internalErrorProvider.get());
        getApplicationSettings().setPageExpiredErrorPage(expiredPageProvider.get());
        getApplicationSettings().setAccessDeniedPage(accessDeniedPageProvider.get());
    }

    private void setBookmarks() {
        for (Bookmark bookmark : bookmarks) {
            mount(bookmark.getMapper());
        }
    }

    private void setMode() {
        if (getConfigurationType().equals(RuntimeConfigurationType.DEPLOYMENT)) {
            getMarkupSettings().setStripWicketTags(true);
            getMarkupSettings().setStripComments(true);
            getMarkupSettings().setCompressWhitespace(true);
        }
    }

    private void setEncoding() {
        getMarkupSettings().setDefaultMarkupEncoding(DEFAULT_ENCODING);
        getRequestCycleSettings().setResponseRequestEncoding(DEFAULT_ENCODING);
    }

    /**
     * {@inheritDoc}
     */
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
     * <p/>
     * Prevents exception on serialization Peaberry Proxy with inject directly to Page.
     *
     * @return The Style Behavior.
     */
    public Behavior getStyleBehavior() {
        return styleBehavior.asBehavior();
    }


    /**
     * Returns Theme behavior gets it from bundle context.
     * <p/>
     * Prevents exception on serialization Peaberry Proxy with inject directly to Page.
     *
     * @return The Theme Behavior.
     */
    public Behavior getThemeBehavior() {
        return themeBehavior.asBehavior();
    }
}
