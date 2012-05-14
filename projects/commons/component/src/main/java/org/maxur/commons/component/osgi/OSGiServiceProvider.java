package org.maxur.commons.component.osgi;

import com.google.inject.Provider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.util.tracker.ServiceTracker;

import static org.maxur.commons.component.utils.ReflectionUtils.getGenericParameterClass;

/**
 * @author Maxim Yunusov
 * @version 1.0 14.05.12
 */
public class OSGiServiceProvider<T> implements Provider<T> {

    private final BundleContext bc;

    public OSGiServiceProvider(final BundleContext bc) {
        this.bc = bc;
    }

    private Filter createFilter(final BundleContext bc) throws InvalidSyntaxException {
        return bc.createFilter(String.format("(objectClass=%s)", getProvidedClass().getName()));
    }

    public final Class getProvidedClass() {
        return getGenericParameterClass(this.getClass(), OSGiServiceProvider.class, 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get() {
        final ServiceTracker tracker;
        try {
            tracker = new ServiceTracker(bc, createFilter(bc), null);
            tracker.open();
            return (T) tracker.getService();
        } catch (InvalidSyntaxException ignore) {
            return null;
        }
    }
}
