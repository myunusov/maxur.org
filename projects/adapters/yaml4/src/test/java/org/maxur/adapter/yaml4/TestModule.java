package org.maxur.adapter.yaml4;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.apache.wicket.behavior.Behavior;
import org.maxur.commons.component.behavior.ThemeBehavior;
import org.maxur.commons.component.model.webclient.WebBrowser;
import org.maxur.commons.view.api.MenuItems;

import static com.google.inject.name.Names.named;
import static org.maxur.commons.component.model.webclient.WebBrowserUtils.ie;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.04.12
 */
public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MenuItems.class).toProvider(MenuItemsProvider.class);
        bind(WebBrowser.class).toInstance(ie(6));
        bind(ThemeBehavior.class).annotatedWith(named("yaml")).to(FakeThemeBehavior.class);
        bindConstant().annotatedWith(Names.named("version")).to("test version");
        bindConstant().annotatedWith(Names.named("service.pid")).to("test pid");
    }

    private static class FakeThemeBehavior extends Behavior implements ThemeBehavior{
        private static final long serialVersionUID = -2768454036238993714L;

        @Override
        public Behavior asBehavior() {
            return this;
        }
    }

}
