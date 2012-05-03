package org.maxur.commons.view.commands;

import com.google.inject.Singleton;
import org.maxur.commons.view.components.menu.GoToCommand;
import org.maxur.commons.view.components.menu.MenuItemsProvider;
import org.maxur.commons.view.components.model.Command;
import org.maxur.commons.view.pages.about.AboutPage;
import org.maxur.commons.view.pages.home.HomePage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 03.05.12
 */
@Singleton
public class MaxurMenuItemsProvider implements MenuItemsProvider {

    private final List<Command> commands;

    public MaxurMenuItemsProvider() {
        // TODO Stub
        commands = new ArrayList<>();
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
    }

    @Override
    public List<Command> getMenuItems() {
        return commands;
    }

}
