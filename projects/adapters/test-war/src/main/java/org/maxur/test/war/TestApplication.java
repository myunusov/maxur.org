package org.maxur.test.war;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * @author Maxim Yunusov
 * @version 1.0 29.05.12
 */
public class TestApplication extends WebApplication {

    public TestApplication() {
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends Page> getHomePage() {
        return TestPage.class;
    }
}