package org.maxur.commons.view;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.junit.Before;
import org.junit.Test;
import org.maxur.commons.view.conf.ApplicationModule;
import org.maxur.commons.view.pages.home.HomePage;

import static org.junit.Assert.assertSame;

/**
 * @author Maxim Yunusov
 * @version 1.0 15.10.11
 */
public class TaskunApplicationTest {

    @Inject
    private WebApplication application;

    @Before
    public void setUp() throws Exception {
        final Injector injector = Guice.createInjector(new ApplicationModule());
        injector.injectMembers(this);
    }

    @Test
    public void shouldBeStartWithHomePage() throws Exception {
        final Class<? extends Page> homePage = application.getHomePage();
        assertSame(HomePage.class, homePage);
    }
}
