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
public abstract class AbstractOSGiServiceProvider<T> implements OSGiServiceProvider<T> {

    private final Class<T> providedClass;

    private ServiceTracker tracker;

    private final Annotation annotation;

    private String pid;

    protected void notifyObserver() {
        GuiceModuleHolder.update(pid);
    }

    public AbstractOSGiServiceProvider(final Class<T> providedClass) {
        this.providedClass = providedClass;
        this.annotation = null;
    }

    public AbstractOSGiServiceProvider(final Class<T> providedClass, final Annotation annotation) {
        this.providedClass = providedClass;
        this.annotation = annotation;
    }

    @Override
    public final Class<T> getProvidedClass() {
        return providedClass;
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
        }
    }

    protected abstract ServiceTracker makeTracker(BundleContext bc) throws InvalidSyntaxException;

    @Override
    public void stop() {
        this.tracker.close();
    }

    @Override
    public void reset(final BundleContext bc) {
        stop();
        start(bc, pid);
    }

    @Override
    public boolean isAnnotated() {
        return this.annotation != null;
    }

    @Override
    public Annotation getAnnotation() {
        return this.annotation;
    }

    protected Filter createFilter(final BundleContext bc) throws InvalidSyntaxException {
        return bc.createFilter(String.format("(objectClass=%s)", providedClass.getName()));
    }
}
