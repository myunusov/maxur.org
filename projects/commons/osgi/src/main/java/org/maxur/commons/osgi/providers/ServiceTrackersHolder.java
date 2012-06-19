package org.maxur.commons.osgi.providers;

import org.maxur.commons.osgi.base.OSGiManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.util.tracker.ServiceTracker;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ServiceTrackersHolder implements OSGiManager {

    private Map<Class, BaseServiceTracker> trackers = new HashMap<>();

    @Override
    public void start(final BundleContext bc, final String pid) {
        for (ServiceTracker tracker : trackers.values()) {
            tracker.open();
        }
    }

    @Override
    public void stop() {
        for (ServiceTracker tracker : trackers.values()) {
            tracker.close();
        }
        trackers.clear();
    }

    private BaseServiceTracker makeTracker(final BundleContext bc, ProvidersGroup group) throws InvalidSyntaxException {
        return new BaseServiceTracker(bc, createFilter(bc, group.getProvidedClass()));
    }

    private Filter createFilter(final BundleContext bc, final Class providedClass) throws InvalidSyntaxException {
        return bc.createFilter(String.format("(objectClass=%s)", providedClass.getName()));
    }

    public void init(final BundleContext bc, final Collection<ProvidersGroup> providersGroups) {
        for (ProvidersGroup group : providersGroups) {
            try {
                BaseServiceTracker tracker = trackers.get(group.getProvidedClass());
                if (null == tracker) {
                    tracker = makeTracker(bc, group);
                    assert tracker != null;
                    trackers.put(group.getProvidedClass(), tracker);
                }
                tracker.add(group);
                //noinspection unchecked
            } catch (InvalidSyntaxException e) {
                assert false : e.getMessage();
            }
        }
    }

}