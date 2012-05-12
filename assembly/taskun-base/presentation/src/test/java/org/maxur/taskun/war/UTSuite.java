package org.maxur.taskun.war;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.taskun.war.config.ApplicationModuleTest;
import org.maxur.taskun.war.pages.UTPageSuite;

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
