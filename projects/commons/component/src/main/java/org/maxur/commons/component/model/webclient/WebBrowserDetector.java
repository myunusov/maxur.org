package org.maxur.commons.component.model.webclient;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public interface WebBrowserDetector extends Serializable {

    WebBrowser detect(HttpServletRequest request);

}
