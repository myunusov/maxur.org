package org.maxur.taskun.war.panels.modal;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

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

    private WebMarkupContainer wmc;

    public static String MODAL_PANEL_ID = "modalPanel";

    @SuppressWarnings("serial")
    public ModalWindow(final String id, final ModalPanel modalPanel) {
        super(id);

        wmc = new WebMarkupContainer("wmc") {
            protected void onComponentTag(final ComponentTag tag) {
                setMarkupId(getMarkupId());
                super.onComponentTag(tag);
            }
        };
        add(wmc);

        wmc.add(modalPanel);
    }

    public String getModalWindowId() {
        return wmc.getMarkupId();
    }

}
