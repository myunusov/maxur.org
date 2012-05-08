package org.maxur.commons.component.model.webclient;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Web Browser description Detector Interface
 *
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public interface WebBrowserDetector extends Serializable {

    /**
     * <p>detect.</p>
     *
     * @param request a {@link javax.servlet.http.HttpServletRequest} object.
     * @return a {@link org.maxur.commons.component.model.webclient.WebBrowser} object.
     */
    WebBrowser detect(HttpServletRequest request);
}
