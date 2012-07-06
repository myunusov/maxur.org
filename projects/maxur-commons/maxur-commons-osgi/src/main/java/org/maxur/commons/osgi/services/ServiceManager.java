package org.maxur.commons.osgi.services;

import org.maxur.commons.core.utils.DictionaryUtils;
import org.maxur.commons.osgi.base.AbstractService;
import org.maxur.commons.osgi.base.OSGiManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Dictionary;

/**
 * @author Maxim Yunusov
 * @version 1.0 13.06.12
 */
public final class ServiceManager extends AbstractService implements OSGiManager {

    private final Serializable service;

    private Class<?> type;

    private ServiceRegistration registration;

    private ServiceFactory factory;

    ServiceManager(final Serializable service) {
        super();
        this.service = service;
    }

    public void start(final BundleContext bc, final String pid) {
        final Dictionary<String, Annotation> properties =
                isAnnotated() ?
                        DictionaryUtils.singleton(ANNOTATION, getAnnotation()) :
                        null;
        registration = bc.registerService(type.getName(), get(pid), properties);
    }

    public void stop() {
        registration.unregister();
    }

    @Override
    public int hashCode() {
        return service.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ServiceManager) {
            final ServiceManager manager = (ServiceManager) obj;
            return service.equals(manager.service);
        } else {
            return super.equals(obj);
        }
    }

    protected Object get(final String pid) {
        if (null == factory) {
            factory = new BaseServiceFactory(this.service, type, pid);
        }
        return factory;
    }

    public void setType(final Class<?> type) {
        this.type = type;
    }
}
