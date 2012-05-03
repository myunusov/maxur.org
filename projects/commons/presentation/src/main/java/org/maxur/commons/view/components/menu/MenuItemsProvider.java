package org.maxur.commons.view.components.menu;

import org.maxur.commons.view.components.model.Command;

import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 03.05.12
 */
public interface MenuItemsProvider {

    List<Command> getMenuItems();

}
