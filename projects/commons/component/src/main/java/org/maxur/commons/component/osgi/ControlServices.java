package org.maxur.commons.component.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim Yunusov
 * @version 1.0 23.05.12
 */
public class ControlServices {

    private final Collection<ServiceRegistration> registrations = new ArrayList<>();

    private final Map<Class<?>, Object> services = new HashMap<>();

    public static ControlServices init() {
        return new ControlServices();
    }

    private ControlServices() {
    }

    public void start(final BundleContext bc) {
        for (Map.Entry<Class<?>, Object> entry : services.entrySet()) {
            final Class<?> servesClass = entry.getKey();
            final Object service = entry.getValue();
            registrations.add(bc.registerService(servesClass.getName(), service, null));
        }
    }

    public void stop() {
        for (ServiceRegistration registration : registrations) {
            registration.unregister();
        }
    }

    public void bind(final Class<?> servesClass, final Object service) {
        assert service.getClass().equals(servesClass);
        services.put(servesClass, service);
    }

}
