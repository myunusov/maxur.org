package org.maxur.commons.osgi.base;

import java.lang.annotation.Annotation;

/**
 * @author Maxim Yunusov
 * @version 1.0 27.05.12
 */
public class ServiceAnnotation {

    public static final String ANNOTATION = "annotation";

    private final Annotation annotation;

    /**
     * The instance constructor.
     *
    */
    public ServiceAnnotation() {
        this.annotation = null;
    }

    /**
     * The instance constructor.
     *
     * @param annotation A service annotation.
     */
    public ServiceAnnotation(final Annotation annotation) {
        this.annotation = annotation;
    }

    /**
     * Returns true if provider must be annotated.
     *
     * @return true if provider must be annotated.
     */
    public boolean isAnnotated() {
        return annotation != null;
    }

    /**
     * Returns service annotation.
     * @return A service annotation.
     */
    public Annotation getAnnotation() {
        return this.annotation;
    }

}
