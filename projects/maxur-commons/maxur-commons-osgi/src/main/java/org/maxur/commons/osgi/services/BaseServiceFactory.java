package org.maxur.commons.osgi.services;

import com.google.inject.Injector;
import org.maxur.commons.core.api.Refresher;
import org.maxur.commons.osgi.holder.InjectorRefresher;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Maxim Yunusov
 * @version 1.0 13.06.12
 */
public class BaseServiceFactory implements ServiceFactory {

    private final Serializable service;

    private final Class<?> interfaceClass;

    private final Refresher<Injector> refresher;

    public BaseServiceFactory(final Serializable service, final Class<?> interfaceClass, final String pid) {
        this.service = service;
        this.interfaceClass = interfaceClass;
        this.refresher = new InjectorRefresher(pid);
    }

    @Override
    public Object getService(final Bundle bundle, final ServiceRegistration serviceRegistration) {
        return Proxy.newProxyInstance(
                service.getClass().getClassLoader(),
                new Class[]{interfaceClass},
                new ServiceHandler(service, refresher)
        );
    }

    @Override
    public void ungetService(final Bundle bundle, final ServiceRegistration serviceRegistration, final Object o) {
    }

    private static class ServiceHandler implements InvocationHandler, Serializable {

        private static final long serialVersionUID = 5586212594157048590L;

        private final Serializable service;
        private final Refresher<Injector> refresher;

        private ServiceHandler(final Serializable service, final Refresher<Injector> refresher) {
            this.service = service;
            this.refresher = refresher;
        }

        public Object invoke(
                final Object proxy,
                final Method method,
                final Object[] args
        ) throws InvocationTargetException, IllegalAccessException {
            if (refresher.isStale()) {
                final Injector injector = refresher.get();
                injector.injectMembers(service);
            }
            return method.invoke(service, args);
        }
    }
}
