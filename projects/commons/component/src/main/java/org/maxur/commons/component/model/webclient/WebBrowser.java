package org.maxur.commons.component.model.webclient;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public interface WebBrowser extends Serializable {

    WebBrowserType getBrowserType();

    String getVersion();

    Integer getMajorVersion();
}
