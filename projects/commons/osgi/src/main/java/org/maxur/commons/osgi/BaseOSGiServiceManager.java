package org.maxur.commons.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
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
        return new BaseServiceTracker(bc, createFilter(bc), serviceDescriptions);
    }

    private Filter createFilter(final BundleContext bc) throws InvalidSyntaxException {
        return bc.createFilter(String.format("(objectClass=%s)", serviceDescriptions.getProvidedClass().getName()));
    }

}
