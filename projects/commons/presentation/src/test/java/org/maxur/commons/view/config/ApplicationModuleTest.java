package org.maxur.commons.view.config;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import org.maxur.commons.view.commands.MaxurMenuItemsProvider;
import org.maxur.commons.view.components.menu.MenuItemsProvider;

import static org.junit.Assert.assertTrue;

/**
 * @author Maxim Yunusov
 * @version 1.0 14.04.12
 */
public class ApplicationModuleTest {

    @Inject
    private MenuItemsProvider menuItemsProvider;

    @Before
    public void setUp() throws Exception {
        final Injector injector = Guice.createInjector(new ApplicationModule());
        injector.injectMembers(this);
    }

    @Test
    public void shouldBeInjectedMaxurMenuItemsProvider() throws Exception {
        assertTrue(menuItemsProvider instanceof MaxurMenuItemsProvider);
    }

}
