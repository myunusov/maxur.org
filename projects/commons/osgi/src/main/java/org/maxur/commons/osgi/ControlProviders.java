package org.maxur.commons.osgi;

import org.osgi.framework.BundleContext;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author Maxim Yunusov
 * @version 1.0 22.05.12
 */
public final class ControlProviders {

    private final Collection<OSGiServiceManager<?>> managers = new HashSet<>();

    private final String pid;

    public static ControlProviders make(final String pid) {
        return new ControlProviders(pid);
    }

    private ControlProviders(final String pid) {
        this.pid = pid;
    }

    public void addServiceManager(final BaseOSGiServiceManager manager) {
        managers.add(manager);
    }

    public ControlProviders start(final BundleContext bc) {
        for (OSGiServiceManager<?> manager : managers) {
            manager.start(bc, pid);
            MutableInjectorHolder.addModule(pid, new ProvidersModule(manager.getServiceDescriptions()));
        }
        return this;
    }

    public void stop() {
        for (OSGiServiceManager<?> manager : managers) {
            manager.stop();
        }
    }

}