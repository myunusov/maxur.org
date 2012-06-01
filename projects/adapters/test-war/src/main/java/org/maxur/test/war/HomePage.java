package org.maxur.test.war;

import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.maxur.commons.component.command.GoToCommand;
import org.maxur.commons.component.mainmenu.MenuItemPanel;
import org.maxur.commons.view.api.Command;
import org.maxur.commons.view.api.MenuItems;
import org.maxur.test.war.kickstrap.TestPage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>6/1/12</pre>
 */
public class HomePage extends WebPage {

    private static final long serialVersionUID = 8131078653112686908L;

    public HomePage() {
        final ListView<Command> listView = new MenuItemsView("menu_items", new Model<>(makeMenuItems()));
        add(listView);
    }

    private MenuItems makeMenuItems() {
        MaxurMenuItems commands = new MaxurMenuItems();
        commands.add(
                GoToCommand.builder()
                        .setAsTarget(HomePage.class)
                        .withTitleKey("home.page.title")
                        .build()
        );
        commands.add(
                GoToCommand.builder()
                        .setAsTarget(TestPage.class)
                        .withTitleKey("kickstrap.page.title")
                        .build()
        );
        return commands;
    }

    private static class MaxurMenuItems extends ArrayList<Command> implements MenuItems  {
        /**
         * The Serial Version UID.
         */
        private static final long serialVersionUID = 8909772836824357933L;
    }


    /**
     * The class is Wicket View of menu items.
     */
    public static class MenuItemsView extends ListView<Command> {

        /**
         * Serial Version UID.
         */
        private static final long serialVersionUID = 8835336241872539442L;

        /**
         * Constructs new MenuItemsView instance.
         *
         * @param id        The MenuItemsView
         * @param menuItems The Menu items List for represent on web.
         */
        public MenuItemsView(final String id, final Model<? extends List<Command>> menuItems) {
            super(id, menuItems);
        }

        /**
         * Populate a given menu items item.
         * @see org.apache.wicket.markup.html.list.ListView#populateItem(
         *                                org.apache.wicket.markup.html.list.ListItem)
         *
         * @param listItem The item to populate
         */
        @Override
        protected void populateItem(final ListItem<Command> listItem) {
            final Command command = listItem.getModelObject();
            listItem.add(new MenuItemPanel("menu_item", new Model<>(command)));
            listItem.add(new SetActiveBehavior(command));
        }

        private static class SetActiveBehavior extends Behavior {

            /**
             * The Serial Version UID.
             */
            private static final long serialVersionUID = -8913795606334638293L;

            private final Command command;

            public SetActiveBehavior(Command command) {
                this.command = command;
            }

            @Override
            public void onComponentTag(final org.apache.wicket.Component component, final ComponentTag tag) {
                super.onComponentTag(component, tag);
                final String className = command.isActive() ? "active" : ""; tag.put("class", className);
            }
        }
    }

    private static class NullMenuItems extends ArrayList<Command> implements MenuItems {
        /**
         * The Serial Version UID.
         */
        private static final long serialVersionUID = 1452204000486091110L;
    }


}
