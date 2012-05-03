package org.maxur.commons.view.config;

import org.ops4j.peaberry.activation.util.PeaberryActivationModule;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.04.12
 */
@SuppressWarnings("UnusedDeclaration")
public class MaxurModule extends PeaberryActivationModule {

    @Override
    protected void configure() {
        requestStaticInjection(GuiceListener.class);
        install(new ApplicationModule());
        install(new WebModule());
        bindConfigurable(String.class).from("org.maxur.commons").named("test.key");
    }

}
