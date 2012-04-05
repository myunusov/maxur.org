package org.maxur.commons.view.conf;

import com.google.inject.AbstractModule;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * @author Maxim Yunusov
 * @version 1.0 28.09.11
 */
public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(WebApplication.class).toProvider(WicketGuiceAppProvider.class);
    }
}