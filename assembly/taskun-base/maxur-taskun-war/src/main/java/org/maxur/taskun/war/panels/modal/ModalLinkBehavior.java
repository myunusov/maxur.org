package org.maxur.taskun.war.panels.modal;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.maxur.adapter.bootstrap.BootstrapCoreBehavior;
import org.maxur.adapter.jquery.JQueryBehavior;

/**
 * Adds the data-toggle and href tags needed for the bootstrap-modal.js
 * Also loads the the js in the header. Requires the id of the modal the
 * js will target.
 *
 * @author drobson
 *
 * see http://www.w3resource.com/twitter-bootstrap/modals-tutorial.php
 *
 */
public class ModalLinkBehavior extends Behavior {

    private static final long serialVersionUID = 488709957973172421L;

    private String modalWindowId;

    public ModalLinkBehavior(final ModalWindow window) {
        this.modalWindowId = window.getMarkupId();
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        tag.put("data-toggle", "modal");
        tag.put("href", "#" + modalWindowId);
        super.onComponentTag(component, tag);
    }

    @Override
    public void bind(Component component) {
        super.bind(component);
        component.add(new JQueryBehavior());
        component.add(new BootstrapCoreBehavior());
       // component.add(new JavaScriptBehavior("modal" + modalWindowId, "$('#" + modalWindowId + "').modal()"));
    }

}