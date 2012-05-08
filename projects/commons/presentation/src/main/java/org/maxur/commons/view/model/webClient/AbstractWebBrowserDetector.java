package org.maxur.commons.view.model.webClient;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class AbstractWebBrowserDetector implements WebBrowserDetector {

    private static final long serialVersionUID = 599180030633091321L;

    private final WebBrowserDetectRules rules;

    {
        rules = (new  WebBrowserDetectRules())
            .add()
                .on("Yahoo! Slurp")
                .set(WebBrowserType.YAHOO_SLURP)
            .add()
                .on("Googlebot/")
                .set(WebBrowserType.GOOGLEBOT)
                .withVersion()
                   .until(";")
            .add()
                .on("msnbot/")
                .set(WebBrowserType.MSNBOT)
                .withVersion()
                    .until(" ")
            .add()
                .on("Chrome/")
                .set(WebBrowserType.CHROME)
                .withVersion()
                    .until(" ")
            .add()
                .on("Safari/")
                .set(WebBrowserType.SAFARI)
                .withVersion()
                    .after("Version/")
                    .until(" ")
            .add()
                .on("Opera Mini/")
                .set(WebBrowserType.OPERA_MINI)
                .withVersion()
                    .until("/")
            .add()
                .on("Opera ")
                .set(WebBrowserType.OPERA)
                .withVersion()
                    .until(" ")
            .add()
                .on("Firefox/")
                .set(WebBrowserType.FIREFOX)
                .withVersion()
                    .until(" ")
            .add()
                .on("MSIE ")
                .set(WebBrowserType.IE)
                .withVersion()
                    .until(";")
            .add()
                .on("Opera/")
                .set(WebBrowserType.OPERA)
                .withVersion()
                    .until(" ");
    }


    @Override
    public final WebBrowser detect(final HttpServletRequest request) {
        return rules.detect(request);
    }

}
