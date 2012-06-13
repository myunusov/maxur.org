package org.maxur.commons.osgi.providers;

import org.maxur.commons.core.api.BaseObservable;
import org.osgi.framework.ServiceReference;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class ProvidersGroup extends BaseObservable implements Iterable<ProviderDescription> {

    private final Map<ServiceReference, ProviderDescription> descriptions = new HashMap<>();

    private final Class providedClass;

    private final boolean canBeMultiple;

    private final Annotation annotation;

    public ProvidersGroup(
            final Class providedClass,
            final boolean canBeMultiple,
            final Annotation annotation
    ) {
        this.providedClass = providedClass;
        this.canBeMultiple = canBeMultiple;
        this.annotation = annotation;
    }

    public void clear() {
        descriptions.clear();
        update();
    }

    public void remove(final ServiceReference reference) {
        descriptions.remove(reference);
        update();
    }

    public ProviderDescription put(final ServiceReference reference, final ProviderDescription description) {
        final ProviderDescription serviceDescription = descriptions.put(reference, description);
        update();
        return serviceDescription;
    }

    @Override
    public Iterator<ProviderDescription> iterator() {
        return descriptions.values().iterator();
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

    boolean hasSameAnnotation(final ProviderDescription description) {
        return isAnnotated() ? annotation.equals(description.getAnnotation()) : !description.isAnnotated();
    }
}