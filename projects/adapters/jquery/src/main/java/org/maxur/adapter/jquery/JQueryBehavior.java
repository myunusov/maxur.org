package org.maxur.adapter.jquery;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.maxur.commons.component.behavior.BaseResourcesBehavior;
import org.maxur.commons.view.api.JScriptBehavior;

/**
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class JQueryBehavior extends BaseResourcesBehavior implements JScriptBehavior {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -6905700536737713853L;

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        response.render(JavaScriptHeaderItem.forReference(
                new JavaScriptResourceReference(this.getClass(), getJQueryCore())
        ));
    }

    public String getJQueryCore() {
        return isDeploymentMode() ? "/jquery-1.7.2.min.js" : "/jquery-1.7.2.js";
    }

}
