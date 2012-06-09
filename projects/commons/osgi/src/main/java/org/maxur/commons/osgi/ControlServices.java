package org.maxur.commons.osgi;

import com.google.inject.Injector;
import org.maxur.commons.core.api.Refresher;
import org.maxur.commons.core.assertion.Assert;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.HashSet;

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
        Assert.argument(interfaceClass).isInterfaceOf(service);
        services.add(ServiceDescription.builder()
                .factory(new OSGiServiceFactory(service, interfaceClass, this.pid))
                .type(interfaceClass)
                .annotation(annotation)
                .build()
        );
    }

    static class OSGiServiceFactory implements ServiceFactory {

        private final Object service;

        private final Class<?> interfaceClass;

        private final Refresher<Injector> refresher;

        public OSGiServiceFactory(final Object service, final Class<?> interfaceClass, final String pid) {
            this.service = service;
            this.interfaceClass = interfaceClass;
            refresher = MutableInjectorHolder.refresher(pid);
        }

        @Override
        public Object getService(final Bundle bundle, final ServiceRegistration serviceRegistration) {

            return Proxy.newProxyInstance
                    (service.getClass().getClassLoader(),
                            new Class[]{interfaceClass},
                            new InvocationHandler() {
                                public Object invoke(
                                        final Object proxy,
                                        final Method method,
                                        final Object[] args
                                ) throws Throwable {
                                    if (refresher.isStale()) {
                                        final Injector injector = refresher.get();
                                        injector.injectMembers(service);
                                    }
                                    return method.invoke(service, args);
                                }
                            });
        }

        @Override
        public void ungetService(final Bundle bundle, final ServiceRegistration serviceRegistration, final Object o) {
        }

    }


}
