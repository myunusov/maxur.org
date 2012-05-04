package org.maxur.commons.view.config;

import org.ops4j.peaberry.activation.util.PeaberryActivationModule;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.04.12
 */
@SuppressWarnings("UnusedDeclaration")
public class OSGiModule extends PeaberryActivationModule {

    public static final String VERSION_KEY = "version";

    @Override
    protected void configure() {
        requestStaticInjection(GuiceListener.class);
        install(new WebModule());
        install(new ApplicationModule());
        bindConfigurable(String.class).from("org.maxur.commons").named(VERSION_KEY);
    }

}