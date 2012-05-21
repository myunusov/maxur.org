package org.maxur.commons.component.osgi;

import com.google.inject.Provider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.util.tracker.ServiceTracker;

import static org.maxur.commons.component.utils.GenericParameters.getParameter;

/**
 * @author Maxim Yunusov
 * @version 1.0 14.05.12
 */
public class OSGiServiceProvider<T> implements Provider<T> {

    private ServiceTracker tracker;

    public OSGiServiceProvider(final BundleContext bc) {
        try {
            tracker = new ServiceTracker(bc, createFilter(bc), null);
            tracker.open();
        } catch (InvalidSyntaxException e) {
            tracker = null;
        }
    }

    private Filter createFilter(final BundleContext bc) throws InvalidSyntaxException {
        return bc.createFilter(String.format("(objectClass=%s)", getProvidedClass().getName()));
    }

    public final Class getProvidedClass() {
        return getParameter(this.getClass(), OSGiServiceProvider.class, 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get() {
        return (T) tracker.getService();
    }

    public void stop() {
        tracker.close();
    }
}
