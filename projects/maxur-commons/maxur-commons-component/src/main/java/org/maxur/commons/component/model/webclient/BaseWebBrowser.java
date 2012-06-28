package org.maxur.commons.component.model.webclient;

/**
 * /**
 * The Web Browser descriptor.
 *
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class BaseWebBrowser implements WebBrowser {

    private static final long serialVersionUID = 4866870325322121791L;

    private static final String VERSION_SEPARATOR = ".";

    private final WebBrowserType type;

    private final String version;

    /**
     * <p>Constructor for BaseWebBrowser.</p>
     *
     * @param type    a {@link org.maxur.commons.component.model.webclient.WebBrowserType} object.
     * @param version a {@link java.lang.String} object.
     */
    public BaseWebBrowser(final WebBrowserType type, final String version) {
        this.type = type;
        this.version = version;
    }

    /**
     * <p>Constructor for BaseWebBrowser.</p>
     *
     * @param type    a {@link org.maxur.commons.component.model.webclient.WebBrowserType} object.
     * @param version a {@link java.lang.String} object.
     */
    public BaseWebBrowser(final WebBrowserType type, final int version) {
        this.type = type;
        this.version = "" + version;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebBrowserType getBrowserType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getVersion() {
        return version;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getMajorVersion() {
        try {
            final String string = version.contains(VERSION_SEPARATOR) ?
                    version.substring(0, version.indexOf(VERSION_SEPARATOR)) : version;
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public boolean lt(final WebBrowser browser) {
        return null != browser &&
                this.getBrowserType().equals(browser.getBrowserType()) &&
                this.getMajorVersion() < browser.getMajorVersion();
    }
}
