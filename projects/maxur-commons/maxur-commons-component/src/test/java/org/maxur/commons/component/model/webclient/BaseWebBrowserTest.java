package org.maxur.commons.component.model.webclient;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Maxim Yunusov
 * @version 1.0 08.05.12
 */
public class BaseWebBrowserTest {

    @Test
    public void shouldBeReturnCorrectType() throws Exception {
        final BaseWebBrowser browser = new BaseWebBrowser(WebBrowserType.OPERA, "11.0");
        assertEquals(WebBrowserType.OPERA, browser.getBrowserType());

    }

    @Test
    public void shouldBeReturnCorrectVersion() throws Exception {
        final BaseWebBrowser browser = new BaseWebBrowser(WebBrowserType.OPERA, "11.0");
        assertEquals("11.0", browser.getVersion());

    }

    @Test
    public void shouldBeReturnCorrectMajorVersion() throws Exception {
        final BaseWebBrowser browser = new BaseWebBrowser(WebBrowserType.OPERA, "11.22");
        assertEquals(new Integer(11), browser.getMajorVersion());

    }

    @Test
    public void shouldBeReturnCorrectMajorVersionWithoutSeparator() throws Exception {
        final BaseWebBrowser browser = new BaseWebBrowser(WebBrowserType.OPERA, "11");
        assertEquals(new Integer(11), browser.getMajorVersion());

    }

    @Test
    public void shouldBeReturnZeroAsMajorVersionOnParseError()  throws Exception {
        final BaseWebBrowser browser = new BaseWebBrowser(WebBrowserType.OPERA, "aaa.11");
        assertEquals(new Integer(0), browser.getMajorVersion());

    }

}
