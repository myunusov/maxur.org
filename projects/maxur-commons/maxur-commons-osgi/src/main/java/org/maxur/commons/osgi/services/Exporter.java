package org.maxur.commons.osgi.services;

import org.maxur.commons.osgi.base.CompositeManager;

import java.io.Serializable;
import java.lang.annotation.Annotation;

import static org.maxur.commons.core.assertion.Contract.argument;

/**
* @author Maxim Yunusov
* @version 1.0 19.06.12
*/
public final class Exporter {

    private final ServiceManager result;

    private final CompositeManager<ServiceManager> manager;

    private final Object service;

    public Exporter(final CompositeManager<ServiceManager> manager, final Serializable service) {
        this.manager = manager;
        this.service = service;
        result = new ServiceManager(service);
    }

    private Exporter type(final Class<?> type) {
        argument(type).isInterfaceOf(service);
        this.result.setType(type);
        return this;
    }

    public Exporter annotatedWith(final Annotation annotation) {
        this.result.setAnnotation(annotation);
        return this;
    }

    private ServiceManager build() {
        return result;
    }

    public void asOSGiService(final Class<?> servesClass) {
        type(servesClass);
        manager.add(build());
    }

}
