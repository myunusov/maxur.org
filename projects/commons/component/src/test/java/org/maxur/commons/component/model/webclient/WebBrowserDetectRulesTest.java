package org.maxur.commons.component.model.webclient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.when;

/**
 * @author Maxim Yunusov
 * @version 1.0 08.05.12
 */
@RunWith(MockitoJUnitRunner.class)
public class WebBrowserDetectRulesTest {

    @Mock
    private HttpServletRequest request;

    private WebBrowserDetectRules rules;

    @Before
    public void setUp() throws Exception {
        rules = WebBrowserDetectRules.get();
    }

    @Test
    public void shouldBeNotSingleton() throws Exception {
        WebBrowserDetectRules rules = WebBrowserDetectRules.get();
        assertNotSame(rules, this.rules);

    }

    @Test
    public void shouldBeDetectUnknownBrowserWithoutAnyRules() throws Exception {
        when(request.getHeader(WebBrowserDetectRules.USER_AGENT)).thenReturn("Test String");
        final WebBrowser browser = rules.detect(request);
        assertEquals(WebBrowserType.UNKNOWN, browser.getBrowserType());
    }

    @Test
    public void shouldBeDetectUnknownBrowserWithEmptyAgentString() throws Exception {
        when(request.getHeader(WebBrowserDetectRules.USER_AGENT)).thenReturn("");
        final WebBrowser browser = rules.detect(request);
        assertEquals(WebBrowserType.UNKNOWN, browser.getBrowserType());
    }

    @Test
    public void shouldBeDetectBrowserTypeByKey() throws Exception {
        when(request.getHeader(WebBrowserDetectRules.USER_AGENT)).thenReturn("key");
        rules.addRuleFor(WebBrowserType.SAFARI).keyString("key");
        final WebBrowser browser = rules.detect(request);
        assertEquals(WebBrowserType.SAFARI, browser.getBrowserType());
    }

    @Test
    public void shouldBeDetectBrowserTypeByKeyFromManyRules() throws Exception {
        when(request.getHeader(WebBrowserDetectRules.USER_AGENT)).thenReturn("key");
        rules.addRuleFor(WebBrowserType.MSNBOT).keyString("error");
        rules.addRuleFor(WebBrowserType.SAFARI).keyString("key");
        final WebBrowser browser = rules.detect(request);
        assertEquals(WebBrowserType.SAFARI, browser.getBrowserType());
    }


    @Test
    public void shouldBeDetectBrowserVersion() throws Exception {
        when(request.getHeader(WebBrowserDetectRules.USER_AGENT)).thenReturn("key7");
        rules.addRuleFor(WebBrowserType.SAFARI).keyString("key").withVersion();
        final WebBrowser browser = rules.detect(request);
        assertEquals("7", browser.getVersion());
    }

    @Test
    public void shouldBeDetectBrowserVersionWithSpace() throws Exception {
        when(request.getHeader(WebBrowserDetectRules.USER_AGENT)).thenReturn("key  7  ");
        rules.addRuleFor(WebBrowserType.SAFARI).keyString("key").withVersion();
        final WebBrowser browser = rules.detect(request);
        assertEquals("7", browser.getVersion());
    }

    @Test
    public void shouldBeDetectBrowserVersionWithSeparator() throws Exception {
        when(request.getHeader(WebBrowserDetectRules.USER_AGENT)).thenReturn("key7;222");
        rules.addRuleFor(WebBrowserType.SAFARI).keyString("key").withVersion().until(";");
        final WebBrowser browser = rules.detect(request);
        assertEquals("7", browser.getVersion());
    }

    @Test
    public void shouldBeDetectBrowserVersionWithSpaceAndSeparator() throws Exception {
        when(request.getHeader(WebBrowserDetectRules.USER_AGENT)).thenReturn("key 7 ; 222");
        rules.addRuleFor(WebBrowserType.SAFARI).keyString("key").withVersion().until(";");
        final WebBrowser browser = rules.detect(request);
        assertEquals("7", browser.getVersion());
    }

    @Test
    public void shouldBeDetectBrowserVersionWithVersionKey() throws Exception {
        when(request.getHeader(WebBrowserDetectRules.USER_AGENT)).thenReturn("key  ver 7");
        rules.addRuleFor(WebBrowserType.SAFARI).keyString("key").withVersion().after("ver ");
        final WebBrowser browser = rules.detect(request);
        assertEquals("7", browser.getVersion());
    }

}
