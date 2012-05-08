package org.maxur.commons.view.model;

import com.google.inject.Provider;
import org.apache.wicket.request.cycle.RequestCycle;
import org.maxur.commons.view.model.webClient.AbstractWebBrowserDetector;
import org.maxur.commons.view.model.webClient.WebBrowser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class WicketWebBrowserDetector
        extends AbstractWebBrowserDetector implements Provider<WebBrowser> {

    private static final long serialVersionUID = -7535296589816859734L;

    public final WebBrowser get() {
        return detect(getHttpServletRequest());
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest());
    }

}
