package org.maxur.commons.osgi.providers;

import org.maxur.commons.osgi.base.CompositeManager;

import java.lang.annotation.Annotation;

/**
* @author Maxim Yunusov
* @version 1.0 19.06.12
*/
public final class Binder {

    private final CompositeManager<ProvidersGroup> manager;

    private final Class providedClass;

    private boolean canBeMultiple;

    private Annotation annotation;

    public Binder(final CompositeManager<ProvidersGroup> manager, final Class providedClass) {
        this.manager = manager;
        this.providedClass = providedClass;
    }

    public Binder single() {
        this.canBeMultiple = false;
        return this;
    }

    public Binder multiple() {
        this.canBeMultiple = true;
        return this;
    }

    public Binder annotatedWith(final Annotation annotation) {
        this.annotation = annotation;
        return this;
    }

    ProvidersGroup build() {
        return new ProvidersGroup(providedClass, canBeMultiple, annotation);
    }

    public void toOSGiService() {
        manager.add(build());
    }
}
