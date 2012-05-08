package org.maxur.commons.component.model.webclient.wicket;

import com.google.inject.Provider;
import org.apache.wicket.ThreadContext;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.commons.component.model.webclient.WebBrowser;
import org.maxur.commons.component.model.webclient.WebBrowserType;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Maxim Yunusov
 * @version 1.0 08.05.12
 */
@RunWith(MockitoJUnitRunner.class)
public class WicketWebBrowserDetectorTest {

    @Mock
    private RequestCycle requestCycle;

    @Mock
    private Request request;

    @Mock
    private HttpServletRequest servletRequest;

    private Provider<WebBrowser> detector;

    @Before
    public void setUp() throws Exception {
        detector = new WicketWebBrowserDetector();
    }

    @Test
    public void testDetect() throws Exception {
        //RequestCycle.get().getRequest().getContainerRequest()
        ThreadContext.setRequestCycle(requestCycle);
        when(requestCycle.getRequest()).thenReturn(request);
        when(request.getContainerRequest()).thenReturn(servletRequest);
        when(servletRequest.getHeader("user-agent")).thenReturn("Chrome/11");
        final WebBrowser browser = detector.get();
        assertEquals(WebBrowserType.CHROME, browser.getBrowserType());
        assertEquals("11", browser.getVersion());

    }
}
