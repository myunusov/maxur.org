package org.maxur.taskun.war.panels.modal;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

/**
* @author Maxim Yunusov
* @version 1.0
* @since <pre>7/10/12</pre>
*/
class JavaScriptBehavior extends Behavior {

    private static final long serialVersionUID = -3397724191884162999L;

    private final String id;

    private final String javascript;

    JavaScriptBehavior(final String id, final String javascript) {
        this.id = id;
        this.javascript = javascript;
    }

    @Override
    public void renderHead(final Component component, final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forScript(javascript, id));
        super.renderHead(component, response);
    }
}
