package org.maxur.commons.view.components.menu;

import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.maxur.commons.view.components.model.Command;
import org.maxur.commons.view.pages.about.AboutPage;
import org.maxur.commons.view.pages.home.HomePage;

import java.util.ArrayList;
import java.util.List;

/**
 * It's menu panel controller class.
 *
 * @author Maxim Yunusov
 * @version 1.0 15.10.11
 */
public class MenuPanel extends Panel {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -3793059102648719177L;

    /**
     * It's menu panel constructor.
     *
     * @param id The non-null id of this component.
     * @see org.apache.wicket.Component#Component(String)
     */
    public MenuPanel(final String id) {
        super(id);
        final ListView<Command> listView = new MenuItemsView("menu_items", getMenuItems());
        add(listView);
    }


    public Model<? extends List<Command>> getMenuItems() {
        // TODO Stub
        final ArrayList<Command> commands = new ArrayList<>();
        commands.add(GoToCommand.Builder.instance()
                .setAsTarget(HomePage.class)
                .withTitleKey("welcome.page.title")
                .build()
        );
        commands.add(GoToCommand.Builder.instance()
                .setAsTarget(AboutPage.class)
                .withTitleKey("about.page.title")
                .build()
        );
        return new Model<>(commands);
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

            //noinspection serial
            listItem.add(new Behavior() {
                @Override
                public void onComponentTag(final org.apache.wicket.Component component, final ComponentTag tag) {
                    super.onComponentTag(component, tag);
                    final String className = command.isActive() ? "active" : ""; tag.put("class", className);
                }
            });
        }

    }
}
