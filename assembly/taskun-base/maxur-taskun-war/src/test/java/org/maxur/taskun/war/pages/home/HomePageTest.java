package org.maxur.taskun.war.pages.home;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.maxur.taskun.war.AbstractWicketTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 15.10.11
 */
public class HomePageTest extends AbstractWicketTest {

    @Inject
    public HomePageTest(Injector injector) {
        super(injector);
    }

    /**
     * Basic Setup of our test.
     */
    @Before
    public void testSetup() {
        getTester().startPage(HomePage.class);
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
        getTester().assertRenderedPage(HomePage.class);
    }


    /**
     * Base Page should be has Header Test case.
     */
    @Test
    @Ignore
    public void shouldBeHasHeader() {
        //getTester().assertComponent("header", HeaderPanel.class);
    }

    /**
     * Base Page should be has Footer Test case.
     */
    @Test
    @Ignore
    public void shouldBeHasFooter() {
        //getTester().assertComponent("footer", FooterPanel.class);
    }

    /**
     * Base Page should be has Menu Test case.
     */
    @Test
    @Ignore
    public void shouldBeHasMenu() {
        //getTester().assertComponent("menu", MenuPanel.class);
    }


}



