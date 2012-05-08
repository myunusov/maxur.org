package org.maxur.commons.view;

import org.maxur.commons.view.model.webClient.WebBrowser;
import org.maxur.commons.view.model.webClient.WebBrowserType;

/**
* @author Maxim Yunusov
* @version 1.0 07.05.12
*/
public class IE6Browser implements WebBrowser {

    private static final long serialVersionUID = 7759211870966009856L;

    @Override
    public WebBrowserType getBrowserType() {
        return WebBrowserType.IE;
    }

    @Override
    public String getVersion() {
        return "6.01";
    }

    @Override
    public Integer getMajorVersion() {
        return 6;
    }
}
