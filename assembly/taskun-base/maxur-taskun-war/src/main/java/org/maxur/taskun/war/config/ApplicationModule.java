package org.maxur.taskun.war.config;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.maxur.commons.component.model.bookmark.BaseBookmark;
import org.maxur.commons.view.api.Bookmarks;
import org.maxur.commons.view.api.MenuItems;
import org.maxur.commons.view.api.PageLister;

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

        bind(MenuItems.class).toProvider(MenuItemsProvider.class);
        bind(new TypeLiteral<Bookmarks<BaseBookmark>>(){}).toProvider(BasePageLister.class);
        bind(new TypeLiteral<PageLister<WebPage>>(){}).to(BasePageLister.class);

        bind(WebApplication.class).toProvider(WicketGuiceAppProvider.class);
    }

}
