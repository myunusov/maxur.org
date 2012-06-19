package org.maxur.commons.osgi.base;

import org.osgi.framework.BundleContext;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author Maxim Yunusov
 * @version 1.0 18.06.12
 */
public class CompositeManager<T extends OSGiManager> implements OSGiManager {

    private final Collection<T> managers = new HashSet<>();

    public void start(final BundleContext bc, final String pid) {
        for (T manager : managers) {
            manager.start(bc, pid);
        }
    }

    public void stop() {
        for (T manager : managers) {
            manager.stop();
        }
        managers.clear();
    }

    public void add(final T manager) {
        managers.add(manager);
    }

    public Collection<T> get() {
        return Collections.unmodifiableCollection(managers);
    }
}
