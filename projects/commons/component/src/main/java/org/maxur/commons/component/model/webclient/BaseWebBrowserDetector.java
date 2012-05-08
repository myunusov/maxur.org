package org.maxur.commons.component.model.webclient;

import javax.servlet.http.HttpServletRequest;

/**
 * Detects Web Browser description By Servlet Request (HttpServletRequest).
 *
 * Thanks  Richard Nichols for rules.
 * see http://www.richardnichols.net/2010/07/detecting-which-browser-in-java-servlet-filter/
 *
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class BaseWebBrowserDetector implements WebBrowserDetector {

    private static final long serialVersionUID = 599180030633091321L;

    private final WebBrowserDetectRules rules;

    {
        rules = WebBrowserDetectRules.get()
            .addRuleFor(WebBrowserType.YAHOO_SLURP)
                .keyString("Yahoo! Slurp")
            .addRuleFor(WebBrowserType.GOOGLEBOT)
                .keyString("Googlebot/")
                .withVersion()
                   .until(";")
            .addRuleFor(WebBrowserType.MSNBOT)
                .keyString("msnbot/")
                .withVersion()
                    .until(" ")
            .addRuleFor(WebBrowserType.CHROME)
                .keyString("Chrome/")
                .withVersion()
                    .until(" ")
            .addRuleFor(WebBrowserType.SAFARI)
                .keyString("Safari/")
                .withVersion()
                    .after("Version/")
                    .until(" ")
            .addRuleFor(WebBrowserType.OPERA_MINI)
                .keyString("Opera Mini/")
                .withVersion()
                    .until("/")
            .addRuleFor(WebBrowserType.OPERA)
                .keyString("Opera ")
                .withVersion()
                    .until(" ")
            .addRuleFor(WebBrowserType.FIREFOX)
                .keyString("Firefox/")
                .withVersion()
                    .until(" ")
            .addRuleFor(WebBrowserType.IE)
                .keyString("MSIE ")
                .withVersion()
                    .until(";")
            .addRuleFor(WebBrowserType.OPERA)
                .keyString("Opera/")
                .withVersion()
                    .until(" ");
    }


    /**
     * {@inheritDoc}
     *
     * Detects Web Browser Type and Version By Servlet Request (HttpServletRequest).
     */
    @Override
    public final WebBrowser detect(final HttpServletRequest request) {
        return rules.detect(request);
    }

}
