package org.maxur.taskun.war.config;

import com.google.inject.Provider;
import org.apache.wicket.protocol.http.WebApplication;
import org.maxur.commons.component.application.MaxurApplication;

/**
 * It would be awesome if we could call addComponentInstantiationListener
 * (new GuiceComponentInjector(app, injector))
 * here instead of passing the injector in the constructor
 * (on injecting it) but GuiceComponentInjector uses InjectorHolder in its constructor
 *
 * @author Maxim Yunusov
 * @version 1.0 15.10.11
 */
public class WicketGuiceAppProvider implements Provider<WebApplication> {

    /**
     * @see com.google.inject.Provider#get()
     *
     * @return An instance of WebApplication. Must never return {@code null}.
     */
    @Override
    public WebApplication get() {
        return new MaxurApplication(BaseTaskunActivator.PID);
    }
}