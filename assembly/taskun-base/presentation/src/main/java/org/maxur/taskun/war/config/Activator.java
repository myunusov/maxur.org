package org.maxur.taskun.war.config;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.cm.ManagedService;
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

    private ControlManager manager = new ControlManager();

    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start(final BundleContext bc) throws Exception {

        logger.info("STARTING org.maxur.commons.config");

        bc.registerService(ManagedService.class.getName(),
                manager.getManagedService(),
                getManagedServiceProperties());
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop(final BundleContext bc) throws Exception {
        logger.info("STOPPING org.maxur.commons.config");
    }

    protected Dictionary<String, String> getManagedServiceProperties() {
        final Dictionary<String, String> result = new Hashtable<>();
        result.put(Constants.SERVICE_PID, PID);
        return result;
    }

}