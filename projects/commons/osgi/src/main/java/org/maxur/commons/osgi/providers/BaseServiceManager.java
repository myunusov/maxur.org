package org.maxur.commons.osgi.providers;

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
public class BaseServiceManager<T> implements ServiceManager<T> {

    private ServiceTracker tracker;

    private String pid;

    private final ProvidersGroup providersGroup;

    public BaseServiceManager(
            final Class<T> providedClass,
            final boolean canBeMultiple,
            final Annotation annotation
    ) {
        providersGroup = new ProvidersGroup(providedClass, canBeMultiple, annotation);
    }

    @Override
    public ProvidersGroup getProvidersGroup() {
        return providersGroup;
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
        providersGroup.clear();
    }

    @Override
    public void reset(final BundleContext bc) {
        stop();
        start(bc, pid);
    }

    protected ServiceTracker makeTracker(final BundleContext bc) throws InvalidSyntaxException {
        return new BaseServiceTracker(bc, createFilter(bc), providersGroup);
    }

    private Filter createFilter(final BundleContext bc) throws InvalidSyntaxException {
        return bc.createFilter(String.format("(objectClass=%s)", providersGroup.getProvidedClass().getName()));
    }

}
