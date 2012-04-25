package org.maxur.commons.view.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * We are using our own WicketFilter subclass called WicketGuiceFilter in order
 * to utilize our application and wire it into wicket's IWebApplicationFactory
 *
 * @author Maxim Yunusov
 * @version 1.0 15.10.11
 */
@Singleton
public class WicketGuiceFilter extends WicketFilter {

    private final WebApplication application;

    @Inject
    public WicketGuiceFilter(final WebApplication application) {
        this.application = application;
    }

    protected IWebApplicationFactory getApplicationFactory() {
        return new IWebApplicationFactory() {
            public WebApplication createApplication(final WicketFilter filter) {
                final Logger logger = LoggerFactory.getLogger(this.getClass());
                logger.debug("Wicket Application has been created");
                return application;
            }

            @Override
            public void destroy(final WicketFilter filter) {
            }
        };
    }



}
