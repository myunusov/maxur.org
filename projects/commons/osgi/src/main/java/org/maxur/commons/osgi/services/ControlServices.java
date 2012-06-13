package org.maxur.commons.osgi.services;

import org.osgi.framework.BundleContext;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashSet;

import static org.maxur.commons.core.assertion.Assert.argument;

/**
 * @author Maxim Yunusov
 * @version 1.0 23.05.12
 */
public final class ControlServices  {

    private String pid;

    private final Collection<ServiceDescription> services = new HashSet<>();

    public static ControlServices make(final String pid) {
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

    public void bind(final Class<?> interfaceClass, final Object service, final Annotation annotation) {
        argument(interfaceClass).isInterfaceOf(service);
        services.add(ServiceDescription.builder()
                .factory(new BaseServiceFactory(service, interfaceClass, this.pid))
                .type(interfaceClass)
                .annotation(annotation)
                .build()
        );
    }


}
