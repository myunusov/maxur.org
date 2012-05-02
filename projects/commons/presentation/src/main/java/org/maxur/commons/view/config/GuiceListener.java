package org.maxur.commons.view.config;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * The GuiceListener class loads our modules.
 *
 * see http://blog.yanivkessler.com/2010/05/wicket-and-guice-alternate-route.html
 */
public class GuiceListener extends GuiceServletContextListener {

    private static Injector injector;

    @Inject
    public static void setInjector(Injector injector) {
        GuiceListener.injector = injector;
    }

    @Override
    protected Injector getInjector() {
        return injector;
    }
}