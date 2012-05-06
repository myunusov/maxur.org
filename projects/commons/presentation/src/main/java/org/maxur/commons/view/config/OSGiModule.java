package org.maxur.commons.view.config;

import org.maxur.commons.view.MaxurApplication;
import org.ops4j.peaberry.activation.util.PeaberryActivationModule;

/**
 * <p>OSGiModule class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 26.04.12
 */
@SuppressWarnings("UnusedDeclaration")
public class OSGiModule extends PeaberryActivationModule {

    /** Constant <code>VERSION_KEY="version"</code> */
    public static final String VERSION_KEY = "version";

    /** {@inheritDoc} */
    @Override
    protected void configure() {
        requestStaticInjection(MaxurApplication.class);
        install(new ApplicationModule());
        bindConfigurable(String.class).from("org.maxur.commons").named(VERSION_KEY);
    }

}
