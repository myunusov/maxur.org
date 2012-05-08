package org.maxur.commons.component.model.webclient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class BaseWebBrowserDetectorTest {

    @Mock
    private HttpServletRequest request;

    private WebBrowserDetector detector;

    @Before
    public void setUp() throws Exception {
        detector = new BaseWebBrowserDetector();
    }

    @Test
    public void testDetect() throws Exception {
        when(request.getHeader(WebBrowserDetectRules.USER_AGENT))
                .thenReturn("Mozilla/5.0 (X11; Linux i686; rv:2.0.1) Gecko/20100101 Firefox/4.0.1");
        final WebBrowser browser = detector.detect(request);
        assertEquals(WebBrowserType.FIREFOX, browser.getBrowserType());
        assertEquals("4.0.1", browser.getVersion());
    }
}
