package org.maxur.commons.it;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Maxim Yunusov
 * @version 1.0 29.09.11
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CommonsBundleIT.class,
        TaskunBundleIT.class
})
public class ITSuite {

    @BeforeClass
    static public void setUp() throws Exception {
    }

    @AfterClass
    static public void tearDown() throws Exception {
    }


}
