package org.maxur.commons.component.osgi;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.multibindings.Multibinder;
import org.osgi.framework.BundleContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim Yunusov
 * @version 1.0 22.05.12
 */
public final class ControlProviders {

    private final Map<Class<?>, SingleOSGiServiceProvider<?>> singleProviders = new HashMap<>();
    private final Map<Class<?>, MultipleOSGiServiceProvider<?>> multipleProviders = new HashMap<>();

    private final String pid;

    private ControlProviders(final String pid) {
        this.pid = pid;
    }

    public static ControlProviders init(final String pid) {
        return new ControlProviders(pid);
    }

    @SuppressWarnings("unchecked")
    public ControlProviders bindSingle(final Class<?> providedClass) {
        singleProviders.put(providedClass, new SingleOSGiServiceProvider(providedClass));
        return this;
    }

    @SuppressWarnings("unchecked")
    public ControlProviders bindMultiple(Class<?> providedClass) {
        multipleProviders.put(providedClass, new MultipleOSGiServiceProvider(providedClass));
        return this;
    }


    public ControlProviders start(final BundleContext bc, final String pid) {
        for (OSGiServiceProvider<?> provider : singleProviders.values()) {
            provider.start(bc, pid);
        }
        for (OSGiServiceProvider<?> provider : multipleProviders.values()) {
            provider.start(bc, pid);
        }
        GuiceModuleHolder.setProvidersModule(this.pid, new ProvidersModule());


        return this;
    }

    public void stop() {
        for (OSGiServiceProvider<?> provider : singleProviders.values()) {
            provider.stop();
        }
    }

    private final class ProvidersModule extends AbstractModule {
        @Override
        protected void configure() {
            for (SingleOSGiServiceProvider<?> provider : singleProviders.values()) {
                if (provider.isAnnotated()) {
                    bind(provider.getProvidedClass())
                            .annotatedWith(provider.getAnnotation())
                            .toProvider((Provider) provider);
                } else {
                    bind(provider.getProvidedClass()).toProvider((Provider) provider);
                }
            }

            for (MultipleOSGiServiceProvider<?> provider : multipleProviders.values()) {
                final Multibinder binder =
                        provider.isAnnotated() ?
                                Multibinder.newSetBinder(
                                        binder(), provider.getProvidedClass(), provider.getAnnotation()
                                ) :
                                Multibinder.newSetBinder(binder(), provider.getProvidedClass());
                for (Object service : provider.getAll()) {
                    //noinspection unchecked
                    binder.addBinding().toInstance(service);
                }
            }
        }
    }
}
