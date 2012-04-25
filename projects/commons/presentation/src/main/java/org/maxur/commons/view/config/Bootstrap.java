package org.maxur.commons.view.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Bootstrap class loads our modules.
 *
 * see http://blog.yanivkessler.com/2010/05/wicket-and-guice-alternate-route.html
 */
public class Bootstrap extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        final Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Guice injector has been created");
        return Guice.createInjector(
                MaxurBundleActivator.getPeaberryModule(),
                new ApplicationModule(),
                new WebModule()
        );
    }
}