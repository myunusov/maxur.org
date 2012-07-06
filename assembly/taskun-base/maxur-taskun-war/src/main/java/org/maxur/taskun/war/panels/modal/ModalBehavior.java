package org.maxur.taskun.war.panels.modal;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

/**
 * Adds the data-toggle and href tags needed for the bootstrap-modal.js
 * Also loads the the js in the header. Requires the id of the modal the
 * js will target.
 *
 * @author drobson
 *
 */
@SuppressWarnings("serial")
public class ModalBehavior extends Behavior {

    private String modalWindowId;

    public ModalBehavior(final String modalWindowId) {
        this.modalWindowId = modalWindowId;
    }

    @Override
    public void renderHead(final Component component, final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forScript(
                "$('#" + modalWindowId + "').modal()", "modal")
        );
        super.renderHead(component, response);
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        tag.put("data-toggle", "modal");
        tag.put("href", "#" + modalWindowId);
        super.onComponentTag(component, tag);
    }
}