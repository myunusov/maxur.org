package org.maxur.commons.view.conf;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import org.apache.wicket.protocol.http.WebApplication;
import org.maxur.commons.view.TaskunApplication;

/**
 * @author Maxim Yunusov
 * @version 1.0 15.10.11
 */


public class WicketGuiceAppProvider implements Provider<WebApplication> {

    private final Injector injector;

    @Inject
    public WicketGuiceAppProvider(final Injector injector) {
        this.injector = injector;
    }

    @Override
    public WebApplication get() {
        return new TaskunApplication(injector);
    }
}
