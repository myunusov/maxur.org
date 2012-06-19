package org.maxur.commons.osgi.providers;

import org.maxur.commons.core.api.BaseObservable;
import org.maxur.commons.osgi.base.MutableInjectorHolder;
import org.maxur.commons.osgi.base.OSGiManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class ProvidersGroup extends BaseObservable implements Iterable<ServiceProvider>, OSGiManager {

    private final Map<ServiceReference, ServiceProvider> providers = new HashMap<>();

    private final Class providedClass;

    private final boolean canBeMultiple;

    private final Annotation annotation;

    ProvidersGroup(final Class providedClass, final boolean canBeMultiple, final Annotation annotation) {
        this.providedClass = providedClass;
        this.canBeMultiple = canBeMultiple;
        this.annotation = annotation;
    }

    @Override
    public void start(BundleContext bc, String pid) {
        MutableInjectorHolder.addModule(pid, new ProvidersModule(this));
    }

    @Override
    public void stop() {
        providers.clear();
        update();
    }

    @Override
    public Iterator<ServiceProvider> iterator() {
        return providers.values().iterator();
    }

    public void remove(final ServiceReference reference) {
        providers.remove(reference);
        update();
    }

    public ServiceProvider put(final ServiceReference reference, final ServiceProvider service) {
        final ServiceProvider provider = providers.put(reference, service);
        update();
        return provider;
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

    boolean hasSameAnnotation(final ServiceProvider service) {
        return isAnnotated() ? annotation.equals(service.getAnnotation()) : !service.isAnnotated();
    }

}