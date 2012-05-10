package org.maxur.commons.view.config;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.maxur.commons.component.model.bookmark.Bookmarks;
import org.maxur.commons.view.api.MenuItems;
import org.maxur.commons.view.api.PageProvider;

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
        bind(PageProvider.class).annotatedWith(Names.named("HomePage")).to(HomePageProvider.class);
        bind(Bookmarks.class).toProvider(BookmarksProvider.class);
        bind(MenuItems.class).toProvider(MenuItemsProvider.class);
    }

}
