package org.maxur.taskun.war;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.apache.wicket.behavior.Behavior;
import org.maxur.commons.component.model.webclient.WebBrowser;
import org.maxur.commons.view.api.MenuItems;
import org.maxur.commons.view.api.StyleBehavior;
import org.maxur.taskun.war.config.MenuItemsProvider;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.04.12
 */
public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MenuItems.class).toProvider(MenuItemsProvider.class);
        bind(WebBrowser.class).to(IE6Browser.class);
        bind(StyleBehavior.class).to(FakeStyleBehavior.class);
        bindConstant().annotatedWith(Names.named("version")).to("test version");
    }


    private static class FakeStyleBehavior extends Behavior implements StyleBehavior{
        private static final long serialVersionUID = -2768454036238993714L;

        @Override
        public Behavior asBehavior() {
            return this;
        }
    }

}