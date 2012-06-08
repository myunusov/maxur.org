package org.maxur.commons.osgi;

import org.osgi.framework.BundleContext;

import java.lang.annotation.Annotation;

/**
 * This interface represent functions to controls OSGi trackers and notify all observers on any changes.
 *
 * @author Maxim Yunusov
 * @version 1.0 24.05.12
 */
public interface OSGiServiceManager<T> {

    ServiceDescriptions getServiceDescriptions();

    Class<T> getProvidedClass();

    /**
     * Start OSGi tracker.
     *
     * @param bc A Bundle Context
     * @param pid A bundle identifier.
     */
    void start(BundleContext bc, String pid);

    /**
     * Stop OSGi tracker.
     */
    void stop();

    /**
     * Reset OSGi tracker.
     */
    void reset(BundleContext bc);

    boolean isMultiple();

    boolean isAnnotated();

    Annotation getAnnotation();
}
