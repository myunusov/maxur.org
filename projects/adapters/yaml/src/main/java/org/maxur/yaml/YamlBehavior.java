package org.maxur.yaml;

import com.google.inject.Inject;
import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.CssResourceReference;
import org.maxur.commons.component.model.webclient.WebBrowser;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.component.model.webclient.WebBrowserType;
import org.maxur.commons.view.api.OSGiWebApplication;
import org.maxur.commons.view.api.StyleBehavior;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class YamlBehavior extends Behavior implements StyleBehavior {

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
        response.render(CssHeaderItem.forReference(new CssResourceReference(this.getClass(), getBasisStylesheets())));
        response.render(CssHeaderItem.forReference(new CssResourceReference(this.getClass(), getScreenLayout())));
        response.render(CssHeaderItem.forReference(new CssResourceReference(this.getClass(), getDruckLayout())));
        if (isOldIE()) {
            response.render(CssHeaderItem.forReference(new CssResourceReference(this.getClass(), getIEHacks())));
        }
    }

    private String getBasisStylesheets() {
        return isDeploymentMode() ? "/yaml/core/slim_base.css" : "/yaml/core/base.css";
    }

    private String getIEHacks() {
        return isDeploymentMode() ?  "/yaml/core/slim_iehacks.css" : "/yaml/core/iehacks.css";
    }

    private String getDruckLayout() {
        return "/yaml/print/print_draft.css";
    }

    private String getScreenLayout() {
        return "/yaml/navigation/nav_shinybuttons.css";
    }


    private boolean isDeploymentMode() {
        final Application application = Application.get();
        return RuntimeConfigurationType.DEPLOYMENT.equals(application.getConfigurationType());
    }

    @Override
    public void bind(final Component component) {
        final Application application = Application.get();
        if (application instanceof OSGiWebApplication) {
            ((OSGiWebApplication) application).registersResource(this);
        }
        super.bind(component);
    }

    @Override
    public Behavior asBehavior() {
        return this;
    }

    private boolean isOldIE() {
        final WebBrowser browser = detector.detect(getHttpServletRequest());
        return WebBrowserType.IE.equals(browser.getBrowserType()) && browser.getMajorVersion() <= IE_7_VERSION;
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest());
    }
}
