package org.maxur.commons.osgi.providers;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import java.util.Collection;
import java.util.HashSet;

/**
* @author Maxim Yunusov
* @version 1.0 09.06.12
*/
class BaseServiceTracker extends ServiceTracker {

    private final Collection<ProvidersGroup> groups = new HashSet<>();

    public BaseServiceTracker(final BundleContext bc, final Filter filter) {
        super(bc, filter, null);
    }

    public void add(final ProvidersGroup group) {
        groups.add(group);
    }

    @Override
    public Object addingService(final ServiceReference reference) {
        final Object service = context.getService(reference);
        assert service != null;
        final ServiceProvider provider = ServiceProvider.builder()
                .reference(reference)
                .instance(service)
                .build();
        for (ProvidersGroup group : groups) {
            if (group.hasSameAnnotation(provider)) {
                group.put(reference, provider);
            }
        }
        return service;
    }

    @Override
    public void removedService(final ServiceReference reference, final Object service) {
        final ServiceProvider provider = ServiceProvider.builder()
                .reference(reference)
                .instance(service)
                .build();
        for (ProvidersGroup group : groups) {
            if (group.hasSameAnnotation(provider)) {
                group.remove(reference);
            }
        }
        context.ungetService(reference);
    }
}
