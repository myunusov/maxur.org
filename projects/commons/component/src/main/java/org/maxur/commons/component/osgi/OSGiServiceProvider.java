package org.maxur.commons.component.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Maxim Yunusov
 * @version 1.0 14.05.12
 */
public class OSGiServiceProvider<T> extends BaseServiceProvider<T> {

    private final Class<T> providedClass;

    private T provider;

    private ServiceTracker tracker;

    public OSGiServiceProvider(final Class<T> providedClass) {
        this.providedClass = providedClass;
    }

    public final Class getProvidedClass() {
        return providedClass;
    }

    @Override
    public T get() {
        if (provider == null) {
            throw new IllegalStateException(String.format("Not found OSGi Service for class '%s'", providedClass));
        }
        if (!providedClass.isAssignableFrom(provider.getClass())) {
            throw new IllegalStateException(
                    String.format("Class %s is not a superclass of %s.", providedClass, provider.getClass()));
        }
        return provider;
    }

    public void start(final BundleContext bc) {
        try {
            this.tracker = makeTracker(bc);
            this.tracker.open();
            //noinspection unchecked
        } catch (InvalidSyntaxException e) {
            this.tracker = null;
        }
    }

    protected ServiceTracker makeTracker(final BundleContext bc) throws InvalidSyntaxException {
        return new ServiceTracker(bc, createFilter(bc), null) {
            @Override
            public Object addingService(final ServiceReference reference) {
                //noinspection unchecked
                provider = (T) this.context.getService(reference);
                return provider;
            }

            @Override
            public void modifiedService(ServiceReference reference, Object service) {
                //noinspection unchecked
                provider = (T) this.context.getService(reference);
            }

            @Override
            public void removedService(ServiceReference reference, Object service) {
            }
        };
    }

    private Filter createFilter(final BundleContext bc) throws InvalidSyntaxException {
        return bc.createFilter(String.format("(objectClass=%s)", getProvidedClass().getName()));
    }

    public void stop() {
        this.tracker.close();
    }

    public void reset(final BundleContext bc) {
        stop();
        start(bc);
    }

}
