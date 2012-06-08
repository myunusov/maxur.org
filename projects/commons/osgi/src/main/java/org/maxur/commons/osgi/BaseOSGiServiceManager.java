package org.maxur.commons.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import java.lang.annotation.Annotation;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>5/21/12</pre>
 */
public class BaseOSGiServiceManager<T> implements OSGiServiceManager<T> {

    private ServiceTracker tracker;

    private String pid;

    private final ServiceDescriptions serviceDescriptions;

    public BaseOSGiServiceManager(
            final Class<T> providedClass,
            final boolean canBeMultiple,
            final Annotation annotation
    ) {
        serviceDescriptions = new ServiceDescriptions(providedClass, canBeMultiple, annotation);
    }

    @Override
    public ServiceDescriptions getServiceDescriptions() {
        return serviceDescriptions;
    }

    @Override
    public void start(final BundleContext bc, final String pid) {
        this.pid = pid;
        try {
            this.tracker = makeTracker(bc);
            this.tracker.open();
            //noinspection unchecked
        } catch (InvalidSyntaxException e) {
            this.tracker = null;
            assert false : e.getMessage();
        }
    }

    @Override
    public void stop() {
        if (null != this.tracker) {
            this.tracker.close();
        }
        serviceDescriptions.clear();
    }

    @Override
    public void reset(final BundleContext bc) {
        stop();
        start(bc, pid);
    }

    protected ServiceTracker makeTracker(final BundleContext bc) throws InvalidSyntaxException {

        return new ServiceTracker(bc, createFilter(bc), null) {

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
        };
    }

    private Filter createFilter(final BundleContext bc) throws InvalidSyntaxException {
        return bc.createFilter(String.format("(objectClass=%s)", serviceDescriptions.getProvidedClass().getName()));
    }

}
