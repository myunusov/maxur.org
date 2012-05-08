package org.maxur.commons.component.model.webclient.wicket;

import com.google.inject.Provider;
import org.apache.wicket.request.cycle.RequestCycle;
import org.maxur.commons.component.model.webclient.BaseWebBrowserDetector;
import org.maxur.commons.component.model.webclient.WebBrowser;

import javax.servlet.http.HttpServletRequest;

/**
 * Detects Web Browser Type and Version By Wicket Context.
 *
 * Provides Web Browser information with Guice IoC container.
 *
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class WicketWebBrowserDetector
        extends BaseWebBrowserDetector implements Provider<WebBrowser> {

    private static final long serialVersionUID = -7535296589816859734L;

    /**
     * Provides Web Browser information with Guice IoC container.
     *
     * @return The Web Browser information.
     */
    public final WebBrowser get() {
        return detect(getHttpServletRequest());
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest());
    }

}
