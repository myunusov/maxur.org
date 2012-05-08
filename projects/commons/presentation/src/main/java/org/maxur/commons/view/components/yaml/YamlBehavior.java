package org.maxur.commons.view.components.yaml;

import com.google.inject.Inject;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.resource.CssResourceReference;
import org.maxur.commons.view.components.StyleBehavior;
import org.maxur.commons.view.model.webClient.WebBrowser;
import org.maxur.commons.view.model.webClient.WebBrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class YamlBehavior extends Behavior implements StyleBehavior {

    private static final long serialVersionUID = -1362412483333201738L;

    private static final int IE_7_VERSION = 7;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private WebBrowser browser;

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        logger.debug(String.format("Request from %s (%s)", browser.getBrowserType(), browser.getVersion()));
        response.render(CssHeaderItem.forReference(
                new CssResourceReference(this.getClass(), "/css/my_layout.css")
        ));
        if (isOldIE(browser)) {
            response.render(CssHeaderItem.forReference(
                    new CssResourceReference(this.getClass(), "/css/patches/patch_my_layout.css")
            ));
        }
    }

    private boolean isOldIE(WebBrowser browser) {
        return WebBrowserType.IE.equals(browser.getBrowserType())  &&  browser.getMajorVersion() <= IE_7_VERSION;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setWebBrowser(final WebBrowser browser) {
        this.browser = browser;
    }
}
