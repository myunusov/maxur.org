package org.maxur.commons.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
* @author Maxim Yunusov
* @version 1.0 09.06.12
*/
class BaseServiceTracker extends ServiceTracker {

    private final ServiceDescriptions serviceDescriptions;

    public BaseServiceTracker(
            final BundleContext bc,
            final Filter filter,
            final ServiceDescriptions serviceDescriptions
    ) {
        super(bc, filter, null);
        this.serviceDescriptions = serviceDescriptions;
    }

    @Override
    public Object addingService(final ServiceReference reference) {
        final Object service = context.getService(reference);
        // TODO must be not null and instance of  providedClass
        final ServiceDescription description = ServiceDescription.builder()
                .reference(reference)
                .type(serviceDescriptions.getProvidedClass())
                .instance(service)
                .build();
        if (serviceDescriptions.hasSameAnnotation(description)) {
            serviceDescriptions.put(reference, description);
        }
        return service;
    }

    @Override
    public void removedService(final ServiceReference reference, final Object service) {
        serviceDescriptions.remove(reference);
        context.ungetService(reference);
    }
}
