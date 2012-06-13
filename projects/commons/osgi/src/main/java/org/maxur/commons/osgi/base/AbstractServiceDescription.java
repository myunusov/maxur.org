package org.maxur.commons.osgi.base;

import java.lang.annotation.Annotation;

/**
 * @author Maxim Yunusov
 * @version 1.0 27.05.12
 */
public abstract class AbstractServiceDescription {

    protected static final String ANNOTATION = "annotation";

    private Annotation annotation;

    protected AbstractServiceDescription() {
    }

    /**
     * Returns true if provider must be annotated.
     *
     * @return true if provider must be annotated.
     */
    public boolean isAnnotated() {
        return annotation != null;
    }

    public Annotation getAnnotation() {
        return this.annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

}
