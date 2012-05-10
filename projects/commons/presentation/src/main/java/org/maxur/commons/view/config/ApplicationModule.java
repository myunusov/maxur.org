package org.maxur.commons.view.config;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.apache.wicket.markup.html.WebPage;
import org.maxur.commons.component.model.bookmark.Bookmarks;
import org.maxur.commons.view.api.MenuItems;
import org.maxur.commons.view.api.PageProvider;
import org.maxur.commons.view.pages.error.AccessDeniedPage;
import org.maxur.commons.view.pages.error.ExpiredPage;
import org.maxur.commons.view.pages.error.InternalErrorPage;
import org.maxur.commons.view.pages.home.HomePage;

/**
 * <p>ApplicationModule class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 28.09.11
 */
public class ApplicationModule extends AbstractModule {

    /** {@inheritDoc} */
    @Override
    protected void configure() {

        bind(Bookmarks.class).toProvider(BookmarksProvider.class);
        bind(MenuItems.class).toProvider(MenuItemsProvider.class);

        bind(PageProvider.class).annotatedWith(Names.named("HomePage")).to(HomePageProvider.class);
        bind(PageProvider.class).annotatedWith(Names.named("InternalErrorPage")).to(InternalErrorPageProvider.class);
        bind(PageProvider.class).annotatedWith(Names.named("ExpiredPage")).to(ExpiredPageProvider.class);
        bind(PageProvider.class).annotatedWith(Names.named("AccessDeniedPage")).to(AccessDeniedPageProvider.class);

    }


    public static class HomePageProvider implements PageProvider {
        @Override
        public Class<? extends WebPage> get() {
            return HomePage.class;
        }
    }

    public static class InternalErrorPageProvider implements PageProvider {
        @Override
        public Class<? extends WebPage> get() {
            return InternalErrorPage.class;
        }
    }

    public static class ExpiredPageProvider implements PageProvider {
        @Override
        public Class<? extends WebPage> get() {
            return ExpiredPage.class;
        }
    }

    public static class AccessDeniedPageProvider implements PageProvider {
        @Override
        public Class<? extends WebPage> get() {
            return AccessDeniedPage.class;
        }
    }



}
