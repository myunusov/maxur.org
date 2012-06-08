package org.maxur.commons.osgi;

import org.maxur.commons.core.api.BaseObservable;
import org.osgi.framework.ServiceReference;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class ServiceDescriptions extends BaseObservable implements Iterable<ServiceDescription> {

    private final Map<ServiceReference, ServiceDescription> serviceDescriptions = new HashMap<>();

    private final Class providedClass;

    private final boolean canBeMultiple;

    private final Annotation annotation;

    public ServiceDescriptions(
            final Class providedClass,
            final boolean canBeMultiple,
            final Annotation annotation
    ) {
        this.providedClass = providedClass;
        this.canBeMultiple = canBeMultiple;
        this.annotation = annotation;
    }

    public void clear() {
        serviceDescriptions.clear();
        update();
    }

    public void remove(final ServiceReference reference) {
        serviceDescriptions.remove(reference);
        update();
    }

    public ServiceDescription put(final ServiceReference reference, final ServiceDescription description) {
        final ServiceDescription serviceDescription = serviceDescriptions.put(reference, description);
        update();
        return serviceDescription;
    }

    @Override
    public Iterator<ServiceDescription> iterator() {
        return serviceDescriptions.values().iterator();
    }

    public boolean isMultiple() {
        return canBeMultiple;
    }

    public Class getProvidedClass() {
        return providedClass;
    }

    public boolean isAnnotated() {
        return null != annotation;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    boolean hasSameAnnotation(final ServiceDescription description) {
        return isAnnotated() ? annotation.equals(description.getAnnotation()) : !description.isAnnotated();
    }
}