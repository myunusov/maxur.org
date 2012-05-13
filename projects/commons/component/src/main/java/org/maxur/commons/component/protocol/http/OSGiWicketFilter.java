package org.maxur.commons.component.protocol.http;

import org.apache.wicket.protocol.http.WicketFilter;
import org.maxur.commons.component.osgi.BundleContext;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 *
 * @author Maxim Yunusov
 * @version 1.0 13.05.12
 */
public class OSGiWicketFilter extends WicketFilter {

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
        BundleContext.persist();
        super.doFilter(request, response, chain);
    }

}
