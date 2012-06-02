package org.maxur.commons.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>5/21/12</pre>
 */
public class BaseOSGiServiceManager<T> implements OSGiServiceManager<T> {

    private final Map<ServiceReference, ServiceDescription> serviceDescriptions = new HashMap<>();

    private final Class<T> providedClass;

    private final boolean isMultiple;

    private final Annotation annotation;

    private ServiceTracker tracker;

    private String pid;

    protected void notifyObserver() {
        MutableInjectorHolder.update(pid);
    }

    public boolean isMultiple() {
        return isMultiple;
    }

    public BaseOSGiServiceManager(
            final Class<T> providedClass,
            final boolean canBeMultiple,
            final Annotation annotation
    ) {
        this.providedClass = providedClass;
        this.isMultiple = canBeMultiple;
        this.annotation = annotation;
    }

    @Override
    public Collection<ServiceDescription> getServiceDescriptions() {
        // TODO must be check for unique annotation in single (non multiple) case
        return serviceDescriptions.values();
    }

    @Override
    public Class<T> getProvidedClass() {
        return providedClass;
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

            public Object addingService(final ServiceReference reference) {
                final Object service = context.getService(reference);
                // TODO must be not null and instance of  providedClass
                final ServiceDescription description = ServiceDescription.builder()
                        .reference(reference)
                        .type(providedClass)
                        .instance(service)
                        .build();
                if (null == annotation ?
                        !description.isAnnotated() :
                        annotation.equals(description.getAnnotation())) {
                    serviceDescriptions.put(reference, description);
                    notifyObserver();
                }
                return service;
            }

            @SuppressWarnings("SuspiciousMethodCalls")
            public void removedService(final ServiceReference reference, final Object service) {
                //noinspection unchecked
                serviceDescriptions.remove(reference);
                context.ungetService(reference);
                notifyObserver();
            }
        };
    }

    private Filter createFilter(final BundleContext bc) throws InvalidSyntaxException {
        return bc.createFilter(String.format("(objectClass=%s)", providedClass.getName()));
    }

    @Override
    public boolean isAnnotated() {
        return null != annotation;
    }

    @Override
    public Annotation getAnnotation() {
        return annotation;
    }
}
