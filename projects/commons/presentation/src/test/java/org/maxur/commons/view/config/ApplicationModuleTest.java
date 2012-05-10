package org.maxur.commons.view.config;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import org.junit.Before;
import org.junit.Test;
import org.maxur.commons.component.model.bookmark.Bookmarks;
import org.maxur.commons.view.api.MenuItems;
import org.maxur.commons.view.api.PageProvider;
import org.maxur.commons.view.pages.home.HomePage;

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
    private Bookmarks bookmarks;

    @Inject @Named("HomePage")
    private PageProvider homePageProvider;


    @Before
    public void setUp() throws Exception {
        final Injector injector = Guice.createInjector(new ApplicationModule());
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
        assertEquals(HomePage.class, homePageProvider.get());
    }

}
