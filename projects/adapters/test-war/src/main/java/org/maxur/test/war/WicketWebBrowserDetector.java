package org.maxur.test.war;

import org.maxur.commons.component.model.webclient.BaseWebBrowserDetector;
import org.maxur.commons.component.model.webclient.WebBrowser;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Maxim Yunusov
 * @version 1.0 31.05.12
 */
public class WicketWebBrowserDetector implements WebBrowserDetector {

    private static final long serialVersionUID = -4204384053169464956L;

    private final WebBrowserDetector detector = new BaseWebBrowserDetector();

    private static ThreadLocal<WebBrowser> threadLocal = new ThreadLocal<>();

    @Override
    public WebBrowser detect(final HttpServletRequest request) {
        WebBrowser browser = threadLocal.get();
        if (null == browser) {
            browser = detector.detect(request);
            threadLocal.set(browser);
        }
        return browser;
    }
}
