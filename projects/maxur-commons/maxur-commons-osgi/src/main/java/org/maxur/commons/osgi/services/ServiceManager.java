package org.maxur.commons.osgi.services;

import org.maxur.commons.core.utils.DictionaryUtils;
import org.maxur.commons.osgi.base.CompositeManager;
import org.maxur.commons.osgi.base.ServiceAnnotation;
import org.maxur.commons.osgi.base.OSGiManager;
import org.maxur.commons.osgi.holder.InjectorRefresher;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Dictionary;

import static org.maxur.commons.core.assertion.Contract.argument;

/**
 * @author Maxim Yunusov
 * @version 1.0 13.06.12
 */
public final class ServiceManager implements OSGiManager {

    private Serializable service;

    private Class<?> type;

    private ServiceAnnotation annotation;

    private ServiceRegistration registration;

    private ServiceFactory factory;

    private ServiceManager() {
    }

    public void start(final BundleContext bc, final String pid) {
        final Dictionary<String, Annotation> properties =
                isAnnotated() ?
                        DictionaryUtils.singleton(ServiceAnnotation.ANNOTATION, getAnnotation()) :
                        null;
        if (null == factory) {
            factory = new BaseServiceFactory(this.service, this.type, new InjectorRefresher(pid));
        }
        registration = bc.registerService(type.getName(), factory, properties);
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

    public boolean isAnnotated() {
        return annotation.isAnnotated();
    }

    public Annotation getAnnotation() {
        return annotation.getAnnotation();
    }


    public static final class Exporter {

        private final CompositeManager<ServiceManager> manager;

        private final Serializable service;

        private Class<?> type;

        private Annotation annotation;

        public Exporter(final CompositeManager<ServiceManager> manager, final Serializable service) {
            this.manager = manager;
            this.service = service;
        }

        public Exporter annotatedWith(final Annotation annotation) {
            this.annotation = annotation;
            return this;
        }

        public void asOSGiService(final Class<?> type) {
            argument(type).isInterfaceOf(this.service);
            this.type = type;
            this.manager.add(build());
        }

        private ServiceManager build() {
            final ServiceManager result = new ServiceManager();
            result.service = this.service;
            result.type = this.type;
            result.annotation = new ServiceAnnotation(this.annotation);
            return result;
        }

    }
}
