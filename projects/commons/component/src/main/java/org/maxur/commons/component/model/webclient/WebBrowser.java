package org.maxur.commons.component.model.webclient;

import java.io.Serializable;

/**
 * The Web Browser descriptor interface.
 *
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public interface WebBrowser extends Serializable {

    /**
     * <p>getBrowserType.</p>
     *
     * @return a {@link org.maxur.commons.component.model.webclient.WebBrowserType} object.
     */
    WebBrowserType getBrowserType();

    /**
     * <p>getVersion.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getVersion();

    /**
     * <p>getMajorVersion.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getMajorVersion();

    boolean lt(WebBrowser browser);
}
