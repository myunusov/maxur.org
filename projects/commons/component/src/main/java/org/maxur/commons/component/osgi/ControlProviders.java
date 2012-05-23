package org.maxur.commons.component.osgi;

import com.google.inject.AbstractModule;
import org.osgi.framework.BundleContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim Yunusov
 * @version 1.0 22.05.12
 */
public final class ControlProviders {

    private final Map<Class<?>, OSGiServiceProvider<?>> providers = new HashMap<>();

    private final String pid;

    private ControlProviders(final String pid) {
        this.pid = pid;
    }

    public static ControlProviders init(final String pid) {
        return new ControlProviders(pid);
    }

    @SuppressWarnings("unchecked")
    public ControlProviders bind(final Class<?> providedClass) {
        providers.put(providedClass, new OSGiServiceProvider(providedClass));
        return this;
    }

    public ControlProviders start(final BundleContext bc) {
        for (OSGiServiceProvider<?> provider : providers.values()) {
            provider.start(bc);
        }
        GuiceModuleHolder.setProvidersModule(pid, new AbstractModule() {
            @Override
            protected void configure() {
                for (OSGiServiceProvider<?> provider : providers.values()) {
                    //noinspection unchecked
                    bind(provider.getProvidedClass()).toProvider(provider);
                }
            }
        });
        return this;
    }

    public void stop() {
        for (OSGiServiceProvider<?> provider : providers.values()) {
            provider.stop();
        }
    }

}
