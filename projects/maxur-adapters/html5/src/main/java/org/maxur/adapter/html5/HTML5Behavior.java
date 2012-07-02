package org.maxur.adapter.html5;

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
public class HTML5Behavior extends BaseResourcesBehavior implements JScriptBehavior {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = 8706570401077541844L;

    @Override
    public final void renderHead(final Component component, final IHeaderResponse response) {
        super.renderHead(component, response);
        response.render(JavaScriptHeaderItem.forReference(
                new JavaScriptResourceReference(this.getClass(), "html5.js")
        ));
    }

}