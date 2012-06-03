package org.maxur.commons.osgi;

import com.google.inject.Injector;
import org.maxur.commons.core.api.Refresher;
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

import static org.maxur.commons.core.api.ArgumentChecker.assertIsInterface;
import static org.maxur.commons.core.api.ArgumentChecker.assertIsInterfaceOf;

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

    public void bind(final Class<?> interfaceClass, final Object service, final Annotation annotation) {
        assertIsInterface(interfaceClass);
        assertIsInterfaceOf(interfaceClass, service);
        services.add(ServiceDescription.builder()
                .factory(new OSGiServiceFactory(service, interfaceClass))
                .type(interfaceClass)
                .annotation(annotation)
                .build()
        );
    }

    class OSGiServiceFactory implements ServiceFactory {

        private final Object service;

        private final Class<?> interfaceClass;

        private final Refresher<Injector> refresher;

        public OSGiServiceFactory(final Object service, Class<?> interfaceClass) {
            this.service = service;
            this.interfaceClass = interfaceClass;
            refresher = MutableInjectorHolder.freshnessController(pid);
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
