package org.maxur.commons.view.config;

import com.google.inject.Module;
import org.ops4j.peaberry.Peaberry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Maxim Yunusov
 * @version 1.0 24.04.12
 */
public class MaxurBundleActivator implements BundleActivator {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static Module peaberryModule;

    @Override
    public void start(final BundleContext bundleContext) {
        logger.info("MaxurBundleActivator is started.");
        peaberryModule = Peaberry.osgiModule(bundleContext);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        logger.info("MaxurBundleActivator is stopped.");
    }

    public static Module getPeaberryModule() {
        return peaberryModule;
    }
}
