package org.maxur.commons.component.protocol.http;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

/**
 * We are using our own WicketFilter subclass called WicketGuiceFilter in order
 * to utilize our application and wire it into wicket's IWebApplicationFactory
 *
 * @author Maxim Yunusov
 * @version 1.0 15.10.11
 */
@Singleton
public class OSGiWicketFilter extends WicketFilter {

    private final WebApplication application;

    @Inject
    public OSGiWicketFilter(final WebApplication application) {
        this.application = application;
    }

    protected IWebApplicationFactory getApplicationFactory() {

        return new IWebApplicationFactory() {
            public WebApplication createApplication(final WicketFilter filter) {
                return application;
            }

            @Override
            public void destroy(final WicketFilter filter) {
            }
        };
    }
}

