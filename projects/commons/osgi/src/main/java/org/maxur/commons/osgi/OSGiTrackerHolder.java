package org.maxur.commons.osgi;

import org.osgi.framework.BundleContext;

import java.lang.annotation.Annotation;

/**
 * @author Maxim Yunusov
 * @version 1.0 24.05.12
 */
public interface OSGiTrackerHolder<T> {

    Class<? extends T> getProvidedClass();

    void start(BundleContext bc, String pid);

    void stop();

    void reset(BundleContext bc);

    boolean isAnnotated();

    Annotation getAnnotation();

}
