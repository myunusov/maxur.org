package org.maxur.commons.component.application;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderResponseDecorator;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.settings.IExceptionSettings;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.markup.head.filter.JavaScriptFilteredIntoFooterHeaderResponse;
import org.maxur.commons.component.application.osgi.AbstractApplication;
import org.maxur.commons.component.model.bookmark.BaseBookmark;
import org.maxur.commons.component.model.webclient.WebBrowser;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.view.api.Bookmarks;
import org.maxur.commons.view.api.PageLister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>MaxurApplication class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 27.09.11
 */
public class MaxurApplication extends AbstractApplication {

    public static final String FOOTER_BUCKET_NAME = "footerJS";

    private static final String DEFAULT_ENCODING = "UTF-8";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    @Named("application.title")
    private String title;

    @Inject
    @Named("application.version")
    private String version;

    @Inject
    private WebBrowserDetector detector;

    @Inject
    private Bookmarks<BaseBookmark> bookmarks;

    @Inject
    private PageLister<WebPage> lister;


    public MaxurApplication(final String pid) {
        super(pid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Class<? extends WebPage> getHomePage() {
        return lister.getStartPage();
    }

    @Override
    protected void doInit() {
        setEncoding();
        setMode();
        setBookmarks();
        setErrorPages();

        setHeaderResponseDecorator(new IHeaderResponseDecorator() {
            public IHeaderResponse decorate(IHeaderResponse response) {
                return new JavaScriptFilteredIntoFooterHeaderResponse(response, FOOTER_BUCKET_NAME);
            }
        });

        logger.debug(String.format("Start %s (Version : %s)", title, version));
    }

    private void setErrorPages() {
        getApplicationSettings().setInternalErrorPage(lister.getInternalErrorPage());
        getApplicationSettings().setPageExpiredErrorPage(lister.getPageExpiredErrorPage());
        getApplicationSettings().setAccessDeniedPage(lister.getAccessDeniedPage());
        mount(new MountedMapper("/404", lister.getPageNotFoundPage()));
    }

    private void setBookmarks() {
        for (BaseBookmark bookmark : bookmarks) {
            mount(bookmark.getMapper());
        }
    }

    private void setMode() {
        if (RuntimeConfigurationType.DEPLOYMENT.equals(getConfigurationType())) {
            getMarkupSettings().setStripWicketTags(true);
            getMarkupSettings().setStripComments(true);
            getMarkupSettings().setCompressWhitespace(true);
            getResourceSettings().setResourcePollFrequency(null);
            getDebugSettings().setComponentUseCheck(false);
            getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
        }
        if (RuntimeConfigurationType.DEVELOPMENT.equals(getConfigurationType())) {
            getResourceSettings().setResourcePollFrequency(Duration.ONE_SECOND);
            getDebugSettings().setComponentUseCheck(true);
            getMarkupSettings().setStripWicketTags(false);
            getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_EXCEPTION_PAGE);
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
     * <p>Setter for the field <code>bookmarks</code>.</p>
     *
     * @param bookmarks a {@link Bookmarks} Iterator of Bookmark.
     */
    @SuppressWarnings("UnusedDeclaration")
    public void setBookmarks(final Bookmarks<BaseBookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    /**
     * <p>Setter for the field <code>lister</code>.</p>
     *
     * @param lister a {@link WebBrowserDetector} Lister of web pages.
     */
    @SuppressWarnings("UnusedDeclaration")
    public void setLister(final PageLister<WebPage> lister) {
        this.lister = lister;
    }

    /**
     * <p>Setter for the field <code>detector</code>.</p>
     *
     * @param detector a {@link WebBrowserDetector} Web Browser Detector.
     */
    @SuppressWarnings("UnusedDeclaration")
    public void setDetector(final WebBrowserDetector detector) {
        this.detector = detector;
    }

    /**
     * <p>Setter for the field <code>version</code>.</p>
     *
     * @param version a {@link java.lang.String} Application Version.
     */
    @SuppressWarnings("UnusedDeclaration")
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * <p>Setter for the field <code>title</code>.</p>
     *
     * @param title a {@link java.lang.String} Application String.
     */
    @SuppressWarnings("UnusedDeclaration")
    public void setTitle(final String title) {
        this.title = title;
    }

}
