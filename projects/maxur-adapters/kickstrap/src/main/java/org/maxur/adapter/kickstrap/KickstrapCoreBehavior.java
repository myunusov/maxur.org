package org.maxur.adapter.kickstrap;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.maxur.commons.component.behavior.BaseResourcesBehavior;
import org.maxur.commons.view.api.StyleBehavior;

/**
 * Import core styles.
 *
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class KickstrapCoreBehavior extends BaseResourcesBehavior implements StyleBehavior {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = 3760270737410463329L;

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        response.render(CssHeaderItem.forReference(
                new CssResourceReference(this.getClass(), getBasisStylesheets())
        ));
        response.render(JavaScriptHeaderItem.forReference(
                new JavaScriptResourceReference(this.getClass(), getModernizr())
        ));

    }

    private String getBasisStylesheets() {
        return "/css/bootstrap.css";
    }

    /**
     * All JavaScript at the bottom, except this Modernizr build.
     * Modernizr enables HTML5 elements & feature detects for optimal performance.
     * Create your own custom Modernizr build: www.modernizr.com/download/
     *
     * @return The modernizr script href.
     */
    private String getModernizr() {
        return "extras/h5bp/js/libs/modernizr-2.5.3.min.js";
    }

}
