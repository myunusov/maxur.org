package org.maxur.adapter.yaml4;

import com.google.inject.Inject;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.maxur.commons.component.behavior.BaseResourcesBehavior;
import org.maxur.commons.component.model.webclient.WebBrowser;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.component.model.webclient.WebBrowserType;
import org.maxur.commons.view.api.StyleBehavior;

import javax.servlet.http.HttpServletRequest;

/**
 * Import core styles.
 *
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class YamlCoreBehavior extends BaseResourcesBehavior implements StyleBehavior {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -1362412483333201738L;

    private static final int IE_7_VERSION = 7;

    @Inject
    private WebBrowserDetector detector;

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        /* Google Font API */
        response.render(CssHeaderItem.forUrl(
            "http://fonts.googleapis.com/css?family=Droid+Serif:400,400italic,700|Droid+Sans:700"
        ));

        response.render(CssHeaderItem.forReference(
                new CssResourceReference(this.getClass(), getBasisStylesheets())
        ));
        if (isOldIE()) {
            response.render(CssHeaderItem.forReference(
                    new CssResourceReference(this.getClass(), getIEHacks())
            ));
        }
        response.render(JavaScriptHeaderItem.forReference(
                new JavaScriptResourceReference(this.getClass(), "/core/js/yaml-focusfix.js"))
        );
    }

    private String getBasisStylesheets() {
        return isDeploymentMode() ? "/core/base.min.css" : "/core/base.css";
    }

    private String getIEHacks() {
        return isDeploymentMode() ?  "/core/iehacks.min.css" : "/core/iehacks.css";
    }

    private boolean isOldIE() {
        final WebBrowser browser = detector.detect(getHttpServletRequest());
        return WebBrowserType.IE.equals(browser.getBrowserType())  &&  browser.getMajorVersion() <= IE_7_VERSION;
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest());
    }
}
