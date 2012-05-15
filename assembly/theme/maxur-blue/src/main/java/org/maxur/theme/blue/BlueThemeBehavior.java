package org.maxur.theme.blue;

import com.google.inject.Inject;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.CssResourceReference;
import org.maxur.commons.component.behavior.BaseResourcesBehavior;
import org.maxur.commons.component.model.webclient.WebBrowser;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.component.model.webclient.WebBrowserType;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class BlueThemeBehavior extends BaseResourcesBehavior {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = 5450247904080075496L;

    private static final int IE_7_VERSION = 7;

    @Inject
    private static WebBrowserDetector detector;

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        response.render(CssHeaderItem.forReference(
                new CssResourceReference(this.getClass(), "/css/layout.css")
        ));
       if (isOldIE()) {
            response.render(CssHeaderItem.forReference(
                    new CssResourceReference(this.getClass(), "/css/patches/patch_layout.css")
            ));
        }
    }

    private boolean isOldIE() {
        final WebBrowser browser = detector.detect(getHttpServletRequest());
        return WebBrowserType.IE.equals(browser.getBrowserType())  &&  browser.getMajorVersion() <= IE_7_VERSION;
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest());
    }
}
