package org.maxur.commons.view.model.webClient;

/**
 * Thanks  Richard Nichols for content.
 * see http://www.richardnichols.net/2010/07/detecting-which-browser-in-java-servlet-filter/
 *
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class WebBrowserBase implements WebBrowser {

    private static final long serialVersionUID = 4866870325322121791L;

    private final WebBrowserType agent;

    private final String version;

    public WebBrowserBase(final WebBrowserType agent, final String version) {
        this.agent = agent;
        this.version = version;
    }

    @Override
    public WebBrowserType getBrowserType() {
        return agent;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public Integer getMajorVersion() {
        try {
            return Integer.parseInt(version.substring(0, version.indexOf(".")));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static WebBrowserDetectRules rules() {
        return new WebBrowserDetectRules();
    }

}
