package org.maxur.commons.osgi.base;

import org.osgi.framework.BundleContext;

/**
 * This interface represent functions to controls OSGi trackers and notify all observers on any changes.
 *
 * @author Maxim Yunusov
 * @version 1.0 24.05.12
 */
public interface OSGiManager {

    /**
     * Start management.
     *
     * @param bc A Bundle Context
     * @param pid A bundle identifier.
     */
    void start(BundleContext bc, String pid);

    /**
     * Stop management.
     */
    void stop();


}
