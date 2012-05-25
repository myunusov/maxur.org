package org.maxur.commons.component.osgi;

import com.google.inject.Provider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Maxim Yunusov
 * @version 1.0 14.05.12
 */
public class SingleOSGiServiceProvider<T> extends AbstractOSGiServiceProvider<T> implements Provider<T> {

    private T provider;

    public SingleOSGiServiceProvider(final Class<T> providedClass) {
        super(providedClass);
    }

    @Override
    public T get() {
        if (provider == null) {
            throw new IllegalStateException(String.format("Not found OSGi Service for class '%s'", getProvidedClass()));
        }
        if (!getProvidedClass().isAssignableFrom(provider.getClass())) {
            throw new IllegalStateException(
                    String.format("Class %s is not a superclass of %s.", getProvidedClass(), provider.getClass()));
        }
        return provider;
    }

    @Override
    protected ServiceTracker makeTracker(final BundleContext bc) throws InvalidSyntaxException {
        return new ServiceTracker(bc, createFilter(bc), null) {
            @Override
            public Object addingService(final ServiceReference reference) {
                //noinspection unchecked
                provider = (T) this.context.getService(reference);
                notifyObserver();
                return provider;
            }
            @Override
            public void modifiedService(ServiceReference reference, Object service) {
                //noinspection unchecked
                provider = (T) this.context.getService(reference);
                notifyObserver();
            }
            @Override
            public void removedService(ServiceReference reference, Object service) {
                notifyObserver();
            }
        };
    }

}
