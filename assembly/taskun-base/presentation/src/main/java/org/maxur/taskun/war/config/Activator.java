package org.maxur.taskun.war.config;

import com.google.inject.Inject;
import org.maxur.commons.component.behavior.ThemeBehavior;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.component.osgi.OSGiServiceProvider;
import org.maxur.commons.component.osgi.WebBundleContext;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Extension of the default OSGi bundle activator.
 *
 * @author Maxim Yunusov
 * @version 1.0 13.05.12
 *
 */
public final class Activator implements BundleActivator {

    private static final Logger logger = LoggerFactory.getLogger(Activator.class);

    private static final String PID = "org.maxur.taskun";

    private OSGiServiceProvider<?>[] providers;

    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start(final BundleContext bc) throws Exception {

        logger.debug("STARTING org.maxur.taskun.war");

        ControlManager.start(bc, getManagedServiceProperties());

        WebBundleContext.setProviders(providers = new OSGiServiceProvider<?>[]{
                new WebBrowserDetectorProvider(bc),
                new ThemeBehaviorProvider(bc)
        });
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop(final BundleContext bc) throws Exception {
        for (OSGiServiceProvider<?> provider : providers) {
            provider.stop();
        }
        logger.debug("STOPPING org.maxur.taskun.war");
    }

    protected Dictionary<String, String> getManagedServiceProperties() {
        final Dictionary<String, String> result = new Hashtable<>();
        result.put(Constants.SERVICE_PID, PID);
        return result;
    }

    public static class WebBrowserDetectorProvider extends OSGiServiceProvider<WebBrowserDetector> {
        @Inject
        public WebBrowserDetectorProvider(BundleContext bc) {
            super(bc);
        }
    }

    public static class ThemeBehaviorProvider extends OSGiServiceProvider<ThemeBehavior> {
        @Inject
        public ThemeBehaviorProvider(BundleContext bc) {
            super(bc);
        }
    }
}