package org.maxur.commons.osgi;

import org.osgi.framework.BundleContext;

/**
 * This interface represent functions to controls OSGi trackers and notify all observers on any changes.
 *
 * @author Maxim Yunusov
 * @version 1.0 24.05.12
 */
public interface OSGiServiceManager<T> {

    /**
     * Start OSGi tracker.
     *
     * @param bc A Bundle Context
     * @param pid A bundle identifier.
     */
    void start(BundleContext bc, String pid);

    /**
     * Stop OSGi tracker.
     */
    void stop();

    /**
     * Reset OSGi tracker.
     */
    void reset(BundleContext bc);

    /**
     * Returns Service Descriptions instance.
     * @return Service Descriptions instance.
     */
    ServiceDescriptions getServiceDescriptions();

}
