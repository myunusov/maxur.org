package org.maxur.commons.osgi.providers;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
* @author Maxim Yunusov
* @version 1.0 09.06.12
*/
class BaseServiceTracker extends ServiceTracker {

    private final ProvidersGroup providersGroup;

    public BaseServiceTracker(
            final BundleContext bc,
            final Filter filter,
            final ProvidersGroup providersGroup
    ) {
        super(bc, filter, null);
        this.providersGroup = providersGroup;
    }

    @Override
    public Object addingService(final ServiceReference reference) {
        final Object service = context.getService(reference);
        // TODO must be not null and instance of  providedClass
        final ProviderDescription description = ProviderDescription.builder()
                .reference(reference)
                .instance(service)
                .build();
        if (providersGroup.hasSameAnnotation(description)) {
            providersGroup.put(reference, description);
        }
        return service;
    }

    @Override
    public void removedService(final ServiceReference reference, final Object service) {
        providersGroup.remove(reference);
        context.ungetService(reference);
    }
}
