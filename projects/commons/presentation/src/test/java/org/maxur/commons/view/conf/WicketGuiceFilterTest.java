package org.maxur.commons.view.conf;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Inject;
import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.junit.Before;
import org.junit.Test;
import org.maxur.commons.view.MaxurApplication;

import static org.junit.Assert.assertTrue;

/**
 * @author Maxim Yunusov
 * @version 1.0 14.04.12
 */
public class WicketGuiceFilterTest {

    @Inject
    private WicketGuiceFilter filter;

    @Before
    public void setUp() throws Exception {
        final Injector injector = Guice.createInjector(new ApplicationModule());
        injector.injectMembers(this);
    }

    @Test
    public void shouldBeReturnFactoryOfMaxurApplication() throws Exception {
        final IWebApplicationFactory factory = filter.getApplicationFactory();
        final WebApplication application = factory.createApplication(filter);
        factory.destroy(filter);
        assertTrue(application instanceof MaxurApplication);
    }


}
