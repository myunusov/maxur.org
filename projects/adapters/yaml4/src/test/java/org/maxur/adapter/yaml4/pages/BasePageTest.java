package org.maxur.adapter.yaml4.pages;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.apache.wicket.markup.html.basic.Label;
import org.junit.Before;
import org.junit.Test;
import org.maxur.adapter.yaml4.AbstractWicketTest;
import org.maxur.commons.component.mainmenu.MenuPanel;

/**
 * @author Maxim Yunusov
 * @version 1.0 15.10.11
 */

public class BasePageTest extends AbstractWicketTest {

    @Inject
    public BasePageTest(Injector injector) {
        super(injector);
    }

    /**
     * Basic Setup of our test.
     */
    @Before
    public void testSetup() {
        getTester().startPage(BasePage.class);
    }

    /**
     * Basic None error Test.
     */
    @Test
    public void shouldBeNoneError() {
        getTester().assertNoErrorMessage();
        getTester().assertNoInfoMessage();
    }

    /**
     * Basic Base Page Test case for our application.
     */
    @Test
    public void shouldBeRendered() {
        getTester().assertRenderedPage(BasePage.class);
    }


    /**
     * Base Page should be has Header Test case.
     */
    @Test
    public void shouldBeHasHeader() {
        getTester().assertComponent("header", HeaderPanel.class);
    }

    /**
     * Base Page should be has Footer Test case.
     */
    @Test
    public void shouldBeHasFooter() {
        getTester().assertComponent("footer", FooterPanel.class);
    }

    /**
     * Base Page should be has Menu Test case.
     */
    @Test
    public void shouldBeHasMenu() {
        getTester().assertComponent("menu", MenuPanel.class);
    }

    @Test
    public void shouldBeHasApplicationTitle() {
        getTester().assertComponent("application.title", Label.class);
    }



}



