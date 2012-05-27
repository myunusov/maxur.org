package org.maxur.adapter.yaml4;

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
import org.maxur.commons.view.api.StyleBehavior;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class YamlBehavior extends BaseResourcesBehavior implements StyleBehavior {

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
        response.render(CssHeaderItem.forReference(
                new CssResourceReference(this.getClass(), "/css/my_layout.css")
        ));
        if (isOldIE()) {
            response.render(CssHeaderItem.forReference(
                    new CssResourceReference(this.getClass(), "/css/patches/patch_my_layout.css")
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
/*


*/
/* Google Font API *//*

@import url(http://fonts.googleapis.com/css?family=Droid+Serif:400,400italic,700|Droid+Sans:700);

*/
/* import core styles | Basis-Stylesheets einbinden *//*

        @import url(../../../resources/org/maxur/adapter/yaml/core/base.css);

*/
/* import screen layout | Screen-Layout einbinden *//*

@import url(../../../resources/org/maxur/adapter/yaml/navigation/hlist.css);
@import url(../../../resources/org/maxur/adapter/yaml/forms/gray-theme.css);

@import url(../../../resources/org/maxur/adapter/yaml/screen/typography.css);
@import url(../../../resources/org/maxur/adapter/yaml/screen/screen-PAGE-layout.css);

*/
/* import print layout | Druck-Layout einbinden *//*

@import url(../../../resources/org/maxur/adapter/yaml/print/print.css);*/
