package org.maxur.commons.osgi.providers;

import org.maxur.commons.osgi.base.MutableInjectorHolder;
import org.osgi.framework.BundleContext;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author Maxim Yunusov
 * @version 1.0 22.05.12
 */
public final class ControlProviders {

    private final Collection<ServiceManager<?>> managers = new HashSet<>();

    private final String pid;

    public static ControlProviders make(final String pid) {
        return new ControlProviders(pid);
    }

    private ControlProviders(final String pid) {
        this.pid = pid;
    }

    public void addServiceManager(final BaseServiceManager manager) {
        managers.add(manager);
    }

    public ControlProviders start(final BundleContext bc) {
        for (ServiceManager<?> manager : managers) {
            manager.start(bc, pid);
            MutableInjectorHolder.addModule(pid, new ProvidersModule(manager.getProvidersGroup()));
        }
        return this;
    }

    public void stop() {
        for (ServiceManager<?> manager : managers) {
            manager.stop();
        }
    }

}