package org.maxur.commons.view.config;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.maxur.commons.component.command.GoToCommand;
import org.maxur.commons.view.api.Command;
import org.maxur.commons.view.api.MenuItems;
import org.maxur.commons.view.pages.about.AboutPage;
import org.maxur.commons.view.pages.home.HomePage;

import java.util.ArrayList;

/**
 * <p>MenuItemsProvider class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 03.05.12
 */
@Singleton
public class MenuItemsProvider implements Provider<MenuItems> {

    private final MenuItems commands;

    /**
     * <p>Constructor for MenuItemsProvider.</p>
     */
    public MenuItemsProvider() {
        // TODO Stub
        commands = new MaxurMenuItems();
        commands.add(
                GoToCommand.builder()
                    .setAsTarget(HomePage.class)
                    .withTitleKey("welcome.page.title")
                    .build()
        );
        commands.add(
                GoToCommand.builder()
                    .setAsTarget(AboutPage.class)
                    .withTitleKey("about.page.title")
                    .build()
        );
    }

    /** {@inheritDoc} */
    @Override
    public MenuItems get() {
        return commands;
    }

    private static class MaxurMenuItems extends ArrayList<Command> implements MenuItems  {
        /**
         * The Serial Version UID.
         */
        private static final long serialVersionUID = 8909772836824357933L;
    }

}
