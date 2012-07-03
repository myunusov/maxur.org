package org.maxur.adapter.bootstrap;

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
import org.maxur.commons.view.api.StyleBehavior;

import javax.servlet.http.HttpServletRequest;

import static org.maxur.commons.component.model.webclient.WebBrowserUtils.ie;

/**
 * Import core styles.
 *
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class BootstrapCoreBehavior extends BaseResourcesBehavior implements StyleBehavior {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = 3760270737410463329L;

    @Inject
    private WebBrowserDetector detector;

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        response.render(CssHeaderItem.forReference(
                new CssResourceReference(this.getClass(), getBasisStylesheets())
        ));
        response.render(JavaScriptHeaderItem.forReference(
                new JavaScriptResourceReference(this.getClass(), getBootstrapJS())
        ));

        if (browser().lt(ie(7))) {
            response.render(CssHeaderItem.forReference(
                    new CssResourceReference(this.getClass(), getIEHacks())
            ));
        }
    }

    private String getBasisStylesheets() {
        return isDeploymentMode() ? "/css/bootstrap.min.css" : "/css/bootstrap.css";
    }

    private String getIEHacks() {
        return isDeploymentMode() ? "/core/iehacks.min.css" : "/css/bootstrap.ie6.css";
    }

    public String getBootstrapJS() {
        return isDeploymentMode() ? "/js/bootstrap.min.js" : "/js/bootstrap.js";
    }

    private WebBrowser browser() {
        return detector.detect(getHttpServletRequest());
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest());
    }


}
