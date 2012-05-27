package org.maxur.commons.osgi;

import com.google.inject.ConfigurationException;
import org.maxur.commons.domain.Observer;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Maxim Yunusov
 * @version 1.0 23.05.12
 */
public final class ControlServices  {

    private String pid;

    private final Collection<ServiceDescription> services = new HashSet<>();

    public static ControlServices init(final String pid) {
        return new ControlServices(pid);
    }

    private ControlServices(final String pid) {
        this.pid = pid;
    }

    public void start(final BundleContext bc) {
        for (ServiceDescription service : services) {
            service.register(bc);
        }
    }

    public void stop() {
        for (ServiceDescription service : services) {
            service.unregister();
        }
    }

    public void bind(final Class<?> servesClass, final Object service, final Annotation annotation) {
        // TODO service must be instance of servesClass
        services.add(ServiceDescription.builder()
                .factory(new OSGiServiceFactory(service))
                .type(servesClass)
                .annotation(annotation)
                .build()
        );
    }

    class OSGiServiceFactory implements ServiceFactory, Observer {

        private final Object service;

        private final MutableInjector injector = MutableInjectorHolder.get(pid);

        public OSGiServiceFactory(final Object service) {
            this.service = service;
            if (null != injector) {
                injector.addObserver(this);
            }
        }

        @Override
        public Object getService(final Bundle bundle, final ServiceRegistration serviceRegistration) {
            return service;
        }

        @Override
        public void ungetService(final Bundle bundle, final ServiceRegistration serviceRegistration, final Object o) {
        }

        @Override
        public void update() {
            if (null != injector) {
                try {
                    injector.inject(service);
                } catch (ConfigurationException ignore) {
                }
            }
        }
    }


}
