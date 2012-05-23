package org.maxur.commons.component.protocol.http;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.maxur.commons.component.osgi.GuiceModuleHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

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

    private final String pid;

    @Inject
    public OSGiWicketFilter(final WebApplication application, @Named("service.pid") final String pid) {
        this.application = application;
        this.pid = pid;
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

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(
            final ServletRequest request,
            final ServletResponse response,
            final FilterChain chain)
            throws IOException, ServletException {
        GuiceModuleHolder.persist(pid);
        super.doFilter(request, response, chain);
    }

}

