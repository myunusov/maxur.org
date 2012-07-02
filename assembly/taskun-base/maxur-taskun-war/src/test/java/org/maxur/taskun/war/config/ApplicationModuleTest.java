package org.maxur.taskun.war.config;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import org.apache.wicket.markup.html.WebPage;
import org.junit.Before;
import org.junit.Test;
import org.maxur.commons.component.model.bookmark.BaseBookmark;
import org.maxur.commons.view.api.Bookmarks;
import org.maxur.commons.view.api.MenuItems;
import org.maxur.commons.view.api.PageLister;
import org.maxur.taskun.war.pages.home.HomePage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Maxim Yunusov
 * @version 1.0 14.04.12
 */
public class ApplicationModuleTest {

    @Inject
    private MenuItems menuItems;

    @Inject
    private Bookmarks<BaseBookmark> bookmarks;

    @Inject
    private PageLister<WebPage> pageLister;


    @Before
    public void setUp() throws Exception {
        final Injector injector = Guice.createInjector(new ApplicationModule(), new AbstractModule() {
            @Override
            protected void configure() {
                bindConstant().annotatedWith(Names.named("service.pid")).to("test pid");
            }
        });
        injector.injectMembers(this);
    }

    @Test
    public void shouldBeInjectedByMenuItems() throws Exception {
        assertNotNull(menuItems);
    }

    @Test
    public void shouldBeInjectedByBookmarks() throws Exception {
        assertNotNull(bookmarks);
    }

    @Test
    public void shouldBeInjectedByHomePage() throws Exception {
        assertEquals(HomePage.class, pageLister.getStartPage());
    }

}
