package org.maxur.taskun.war.panels.modal;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Adds the content for the @BootstrapModalWindow.
 *
 * Contains header, content and button sections.
 * Content is provided by the supplied panel from the getContent method.
 *
 *
 * @author drobson
 *
 */
public abstract class ModalPanel extends Panel {

    private static final long serialVersionUID = -5374709317282588892L;

    @SuppressWarnings("serial")
    public ModalPanel(String id, String header) {
        super(id);

        //Modal window header
        Label label = new Label("header", header);
        add(label);

        //the windows content
        add(ModalPanel.this.getContent("content"));

        //Buttons
        AjaxLink cancel = new AjaxLink("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                ModalPanel.this.onCancel(target);
            }
        };
        add(cancel);

        AjaxLink submit = new AjaxLink("submit") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                ModalPanel.this.onSubmit(target);
            }
        };
        add(submit);
    }

    public abstract Panel getContent(String id);

    public abstract void onCancel(AjaxRequestTarget target);

    public abstract void onSubmit(AjaxRequestTarget target);

}
