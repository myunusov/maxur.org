package org.maxur.commons.view.pages;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * It's header panel controller class.
 *
 * @author Maxim Yunusov
 * @version 1.0 15.10.11
 */
public class HeaderPanel extends Panel {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = 9063122762266182766L;

    /**
     * It's header panel constructor.
     *
     * @param id The non-null id of this component.
     * @see org.apache.wicket.Component#Component(String)
     */
    public HeaderPanel(final String id) {
        super(id);
    }
}
