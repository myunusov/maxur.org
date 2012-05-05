package org.maxur.commons.view.config;

import com.google.inject.AbstractModule;
import org.maxur.commons.view.commands.MaxurMenuItemsProvider;
import org.maxur.commons.view.components.menu.MenuItemsProvider;

/**
 * @author Maxim Yunusov
 * @version 1.0 28.09.11
 */
public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MenuItemsProvider.class).to(MaxurMenuItemsProvider.class);
    }
}
