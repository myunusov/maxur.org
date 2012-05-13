package org.maxur.commons.component.application;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.settings.IMarkupSettings;
import org.apache.wicket.settings.IRequestCycleSettings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Locale;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Maxim Yunusov
 * @version 1.0 15.10.11
 */
@RunWith(MockitoJUnitRunner.class)
public class MaxurApplicationTest {

    @Mock
    private Request request;

    @Mock
    private IMarkupSettings markupSettings;

    @Mock
    private IRequestCycleSettings requestCycleSettings;

    private WebApplication application;

    @Before
    public void setUp() throws Exception {
        application = new TestMaxurApplication();
        application.setMarkupSettings(markupSettings);
        application.setRequestCycleSettings(requestCycleSettings);
    }

    @Test
    public void shouldBeUsedMaxurSession() throws Exception {
        when(request.getLocale()).thenReturn(Locale.US);
        final Session session = application.newSession(request, null);
        assertTrue(session instanceof MaxurSession);
    }

    private static class TestMaxurApplication extends MaxurApplication {
        public TestMaxurApplication() {
            super();
        }
    }

}
