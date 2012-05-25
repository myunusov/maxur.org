package org.maxur.commons.component.config;

import org.maxur.commons.component.model.webclient.BaseWebBrowserDetector;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.component.osgi.BaseGuiceActivator;

/**
 * @author Maxim Yunusov
 * @version 1.0 23.05.12
 */
@SuppressWarnings("UnusedDeclaration")
public class CommonsComponentActivator extends BaseGuiceActivator {

    private static final String PID = "org.maxur.commons.component";

    public CommonsComponentActivator() {
        super(PID);
    }

    @Override
    protected void config() {
        export(WebBrowserDetector.class, new BaseWebBrowserDetector());
    }

}
