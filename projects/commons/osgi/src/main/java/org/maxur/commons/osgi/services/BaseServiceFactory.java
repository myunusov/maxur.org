package org.maxur.commons.osgi.services;

import com.google.inject.Injector;
import org.maxur.commons.core.api.Refresher;
import org.maxur.commons.osgi.base.MutableInjectorHolder;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Maxim Yunusov
 * @version 1.0 13.06.12
 */
class BaseServiceFactory implements ServiceFactory {

    private final Object service;

    private final Class<?> interfaceClass;

    private final Refresher<Injector> refresher;

    public BaseServiceFactory(final Object service, final Class<?> interfaceClass, final String pid) {
        this.service = service;
        this.interfaceClass = interfaceClass;
        refresher = MutableInjectorHolder.refresher(pid);
    }

    @Override
    public Object getService(final Bundle bundle, final ServiceRegistration serviceRegistration) {
        return Proxy.newProxyInstance(
                service.getClass().getClassLoader(),
                new Class[]{interfaceClass},
                new ServiceHandler()
        );
    }

    @Override
    public void ungetService(final Bundle bundle, final ServiceRegistration serviceRegistration, final Object o) {
    }

    private class ServiceHandler implements InvocationHandler {
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
