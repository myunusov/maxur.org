package org.maxur.taskun.war.panels.modal;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.panel.Panel;
import org.maxur.taskun.war.pages.about.AboutPanel;

/**
 *
 * A wicket version of the bootstrap Modal.
 * http://twitter.github.com/bootstrap/javascript.html#modals
 * http://www.david-robson.co.uk/?p=162
 *
 * This this just a window the content is provided by the @BootstrapModalPanel.
 *
 * @author drobson
 *
 */public class ModalWindow extends Panel {

    private static final long serialVersionUID = -5805548092000080511L;

    @SuppressWarnings("serial")
    public ModalWindow(final String id) {
        super(id);
        add(new AttributeAppender("class", "modal hide fade in"));
        add(new AttributeAppender("style", "display: none;"));
        setOutputMarkupId(true);
        final ModalPanel modalPanel = new ModalPanel("modal_panel", "This is a Modal Heading") {
            @Override
            public void onSubmit(AjaxRequestTarget target) {
            }

            @Override
            public void onCancel(AjaxRequestTarget target) {
            }

            @Override
            public Panel getContent(String id) {
                return new AboutPanel(id);
            }
        };
        add(modalPanel);
    }

}
