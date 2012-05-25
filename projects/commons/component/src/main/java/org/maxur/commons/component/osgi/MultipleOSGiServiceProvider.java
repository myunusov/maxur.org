package org.maxur.commons.component.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 24.05.12
 */
public class MultipleOSGiServiceProvider<T> extends AbstractOSGiServiceProvider<T>  {

    private final List<T> list = new ArrayList<>();

    public List<T> getAll() {
        return list;
    }

    public MultipleOSGiServiceProvider(final Class<T> providedClass) {
        super(providedClass);
    }

    @Override
    protected ServiceTracker makeTracker(final BundleContext bc) throws InvalidSyntaxException {

        return new ServiceTracker(bc, createFilter(bc), null)  {

            public Object addingService(final ServiceReference reference) {
                //noinspection unchecked
                final T provider = (T) context.getService(reference);
                list.add(provider);
                notifyObserver();
                return provider;
            }

            @SuppressWarnings("SuspiciousMethodCalls")
            public void removedService(final ServiceReference reference, final Object service) {
                //noinspection unchecked
                list.remove(service);
                context.ungetService(reference);
                notifyObserver();
            }
        };
    }




}
