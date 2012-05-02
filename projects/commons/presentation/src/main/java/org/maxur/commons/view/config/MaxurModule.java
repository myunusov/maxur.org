package org.maxur.commons.view.config;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.ops4j.peaberry.activation.util.PeaberryActivationModule;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.04.12
 */
public class MaxurModule extends PeaberryActivationModule {

    @Inject
    private Injector injector;

    @Override
    protected void configure() {
        requestStaticInjection(GuiceListener.class);
        install(new ApplicationModule());
        install(new WebModule());
        bindConfigurable(String.class).from("org.maxur.commons").named("test.key");
    }

}
