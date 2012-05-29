package org.maxur.commons.component.model.webclient;

/**
 * @author Maxim Yunusov
 * @version 1.0 29.05.12
 */
public final class WebBrowserUtils {

    public static WebBrowser ie(int version) {
        return new BaseWebBrowser(WebBrowserType.IE, version);
    }

}
