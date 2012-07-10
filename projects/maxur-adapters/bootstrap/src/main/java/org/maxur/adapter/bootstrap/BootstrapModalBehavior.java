package org.maxur.adapter.bootstrap;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.maxur.commons.component.behavior.BaseResourcesBehavior;
import org.maxur.commons.view.api.StyleBehavior;

/**
 * Import core styles.
 *
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class BootstrapModalBehavior extends BaseResourcesBehavior implements StyleBehavior {

    private static final long serialVersionUID = 3171045059517933568L;

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        response.render(JavaScriptHeaderItem.forReference(
                new JavaScriptResourceReference(this.getClass(), getBootstrapJS())
        ));
    }

    private String getBootstrapJS() {
        return "/js/bootstrap-modal.js";
    }

}
