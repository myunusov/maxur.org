package org.maxur.commons.osgi.base;

import org.osgi.framework.BundleContext;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 *  This class represents Composite of OSGiManager classes.
 *  see Composite Gof pattern.
 *
 * @author Maxim Yunusov
 * @version 1.0 18.06.12
 */
public class CompositeManager<T extends OSGiManager> implements OSGiManager {

    private final Collection<T> managers = new HashSet<>();

    /**
     * @see org.maxur.commons.osgi.base.OSGiManager#start(org.osgi.framework.BundleContext, String)
     *
     * @param bc A Bundle Context
     * @param pid A bundle identifier.
     */
    @Override
    public void start(final BundleContext bc, final String pid) {
        for (T manager : managers) {
            manager.start(bc, pid);
        }
    }

    /**
     * @see org.maxur.commons.osgi.base.OSGiManager#stop()
     */
    @Override
    public void stop() {
        for (T manager : managers) {
            manager.stop();
        }
        managers.clear();
    }

    /**
     * Add new child manager
     * @param manager a new child manager
     */
    public void add(final T manager) {
        managers.add(manager);
    }

    /**
     * Returns all managers as collection.
     *
     * @return The collection of managers.
     */
    public Collection<T> get() {
        return Collections.unmodifiableCollection(managers);
    }
}
