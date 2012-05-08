package org.maxur.commons.view;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.maxur.commons.view.commands.MaxurMenuItemsProvider;
import org.maxur.commons.view.components.menu.MenuItemsProvider;
import org.maxur.commons.view.model.webClient.WebBrowser;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.04.12
 */
public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MenuItemsProvider.class).to(MaxurMenuItemsProvider.class);
        bind(WebBrowser.class).to(IE6Browser.class);
        bindConstant().annotatedWith(Names.named("version")).to("test version");
    }

}
