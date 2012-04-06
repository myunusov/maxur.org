package org.maxur.commons.view;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.commons.view.pages.home.HomePageTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 29.09.11
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BasePageTest.class,
        HomePageTest.class
})
public class UTPageSuite {
}
