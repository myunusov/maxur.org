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

    private static Injector osgiInjector;

    @Inject
    public static void setOsgiInjector(Injector osgiInjector) {
        GuiceListener.osgiInjector = osgiInjector;
    }

    @Override
    protected Injector getInjector() {
        return osgiInjector;
    }

}