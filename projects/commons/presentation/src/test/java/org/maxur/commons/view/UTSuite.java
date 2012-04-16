package org.maxur.commons.view;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.commons.view.conf.ApplicationModuleTest;
import org.maxur.commons.view.pages.UTPageSuite;

/**
 * @author Maxim Yunusov
 * @version 1.0 29.09.11
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        UTPageSuite.class,
        MaxurApplicationTest.class,
        ApplicationModuleTest.class
})
public class UTSuite {
}
