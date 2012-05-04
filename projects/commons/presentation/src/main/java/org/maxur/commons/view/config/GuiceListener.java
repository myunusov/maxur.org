package org.maxur.commons.view.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * The GuiceListener class loads our modules.
 *
 * see http://blog.yanivkessler.com/2010/05/wicket-and-guice-alternate-route.html
 */
public class GuiceListener extends GuiceServletContextListener {

    private Injector webInjector;

    @Override
    protected Injector getInjector() {
        webInjector = webInjector == null ? createInjector() : webInjector;
        return webInjector;
    }

    private Injector createInjector() {
        return Guice.createInjector(new WebModule(), new ApplicationModule(), new OSGiAdapterModule());
    }

}