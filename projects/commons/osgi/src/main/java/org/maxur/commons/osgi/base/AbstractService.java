package org.maxur.commons.osgi.base;

import java.lang.annotation.Annotation;

/**
 * @author Maxim Yunusov
 * @version 1.0 27.05.12
 */
public abstract class AbstractService {

    protected static final String ANNOTATION = "annotation";

    private Annotation annotation;

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

    /**
     * Set service annotation.
     * @param annotation A service annotation.
     */
    public void setAnnotation(final Annotation annotation) {
        this.annotation = annotation;
    }

}
