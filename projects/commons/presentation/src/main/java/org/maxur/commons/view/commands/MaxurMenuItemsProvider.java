package org.maxur.commons.view.commands;

import com.google.inject.Singleton;
import org.maxur.commons.component.command.GoToCommand;
import org.maxur.commons.view.components.menu.MenuItemsProvider;
import org.maxur.commons.component.command.Command;
import org.maxur.commons.view.pages.about.AboutPage;
import org.maxur.commons.view.pages.home.HomePage;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>MaxurMenuItemsProvider class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 03.05.12
 */
@Singleton
public class MaxurMenuItemsProvider implements MenuItemsProvider {

    private final List<Command> commands;

    /**
     * <p>Constructor for MaxurMenuItemsProvider.</p>
     */
    public MaxurMenuItemsProvider() {
        // TODO Stub
        commands = new ArrayList<>();
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
    public List<Command> getMenuItems() {
        return commands;
    }

}
