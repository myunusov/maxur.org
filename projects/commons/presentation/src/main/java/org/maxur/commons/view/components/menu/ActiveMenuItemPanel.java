package org.maxur.commons.view.components.menu;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.maxur.commons.view.api.Command;

/**
 * <p>ActiveMenuItemPanel class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 15.04.12
 */
public class ActiveMenuItemPanel extends Panel {

    private static final long serialVersionUID = -3987722390295410450L;

    /**
     * <p>Constructor for ActiveMenuItemPanel.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @param item a {@link org.apache.wicket.model.Model} object.
     */
    public ActiveMenuItemPanel(String id, Model<Command> item) {
        super(id, item);
    }
}
