package org.maxur.commons.view.components.menu;

import org.maxur.commons.view.components.model.Command;

import java.util.List;

/**
 * <p>MenuItemsProvider interface.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 03.05.12
 */
public interface MenuItemsProvider {

    /**
     * <p>getMenuItems</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<Command> getMenuItems();

}
