package org.maxur.commons.view.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;
import org.maxur.commons.view.components.menu.MenuPanel;

/**
 * @author Maxim Yunusov
 * @version 1.0 15.10.11
 */
public class BasePageTest {

    protected static WicketTester tester;

    /**
     * Basic Setup of our test.
     */
    @BeforeClass
    public static void testSetup() {
        tester = new WicketTester();
        tester.startPage(BasePage.class);
    }


    /**
     * Basic None error Test.
     */
    @Test
    public void shouldBeNoneError() {
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }


    /**
     * Basic Base Page Test case for our application.
     */
    @Test
    public void shouldBeRendered() {
        tester.assertRenderedPage(BasePage.class);
    }


    /**
     * Base Page should be has Header Test case.
     */
    @Test
    public void shouldBeHasHeader() {
        tester.assertComponent("header", HeaderPanel.class);
    }

    /**
     * Base Page should be has Footer Test case.
     */
    @Test
    public void shouldBeHasFooter() {
        tester.assertComponent("footer", FooterPanel.class);
    }

    /**
     * Base Page should be has Menu Test case.
     */
    @Test
    public void shouldBeHasMenu() {
        tester.assertComponent("menu", MenuPanel.class);
    }

    @Test
    public void shouldBeHasApplicationTitle() {
        tester.assertComponent("application.title", Label.class);
    }



}



