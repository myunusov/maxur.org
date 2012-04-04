package org.maxur.commons.view.conf;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

/**
 * @author Maxim Yunusov
 * @version 1.0 15.10.11
 */
@Singleton
public class WicketGuiceFilter extends WicketFilter {

    @Inject
    private Provider<WebApplication> provider;

    @Override
    protected IWebApplicationFactory getApplicationFactory() {

        return new IWebApplicationFactory() {
            @Override
            public WebApplication createApplication(final WicketFilter filter) {
                return provider.get();
            }

            @Override
            public void destroy(final WicketFilter filter) {

            }
        };
    }
}
