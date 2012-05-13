package org.maxur.taskun.war.config;

import org.maxur.commons.component.behavior.ThemeBehavior;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.taskun.war.MaxurApplication;
import org.ops4j.peaberry.activation.util.PeaberryActivationModule;

/**
 * <p>OSGiModule class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 26.04.12
 */
@SuppressWarnings("UnusedDeclaration")
public class OSGiModule extends PeaberryActivationModule {

    private static final String VERSION_KEY = "application.version";

    private static final String APPLICATION_KEY = "application.title";

    /** {@inheritDoc} */
    @Override
    protected void configure() {
        bindConfigurable(String.class).from("org.maxur.commons").named(VERSION_KEY);
        bindConfigurable(String.class).from("org.maxur.commons").named(APPLICATION_KEY);

        bindService(ThemeBehavior.class).single();
        bindService(WebBrowserDetector.class).single();
        install(new ApplicationModule());
        requestStaticInjection(MaxurApplication.class);
    }

}
