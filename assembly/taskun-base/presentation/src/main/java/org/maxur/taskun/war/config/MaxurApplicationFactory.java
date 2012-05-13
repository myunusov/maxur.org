package org.maxur.taskun.war.config;

import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.maxur.commons.component.application.MaxurApplication;

/**
 * @author Maxim Yunusov
 * @version 1.0 13.05.12
 */
public class MaxurApplicationFactory implements IWebApplicationFactory {

    @Override
    public WebApplication createApplication(final WicketFilter filter) {
        return new MaxurApplication();
    }

    @Override
    public void destroy(final WicketFilter filter) {
    }

}
