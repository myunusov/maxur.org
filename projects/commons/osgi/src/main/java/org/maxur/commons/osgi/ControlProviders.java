package org.maxur.commons.osgi;

import com.google.inject.AbstractModule;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.multibindings.Multibinder;
import org.osgi.framework.BundleContext;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim Yunusov
 * @version 1.0 22.05.12
 */
public final class ControlProviders {

    private final Map<Class<?>, OSGiServiceManager<?>> providers = new HashMap<>();

    private final String pid;

    private ControlProviders(final String pid) {
        this.pid = pid;
    }

    public static ControlProviders init(final String pid) {
        return new ControlProviders(pid);
    }

    public void addServiceManager(final Class<?> providedClass, final BaseOSGiServiceManager manager) {
        providers.put(providedClass, manager);
    }

    public ControlProviders start(final BundleContext bc) {
        for (OSGiServiceManager<?> provider : providers.values()) {
            provider.start(bc, this.pid);
        }
        MutableInjectorHolder.get(this.pid).addModule(new ProvidersModule());
        return this;
    }

    public void stop() {
        for (OSGiServiceManager<?> provider : providers.values()) {
            provider.stop();
        }
    }

    private final class ProvidersModule extends AbstractModule {
        @Override
        protected void configure() {
            for (OSGiServiceManager<?> provider : providers.values()) {
                final Collection<ServiceDescription> descriptions = provider.getServiceDescriptions();
                if (provider.isMultiple()) {
                    final Multibinder binder;
                    if (provider.isAnnotated()) {
                        binder = Multibinder.newSetBinder(
                                binder(), provider.getProvidedClass(), provider.getAnnotation()
                        );
                    } else {
                        binder = Multibinder.newSetBinder(binder(), provider.getProvidedClass());
                    }
                    for (ServiceDescription description : descriptions) {
                        //noinspection unchecked
                        binder.addBinding().toProvider(description);
                    }
                } else {
                    for (ServiceDescription description : descriptions) {
                        @SuppressWarnings("unchecked")
                        final AnnotatedBindingBuilder<Object> bind =
                                (AnnotatedBindingBuilder<Object>) bind(provider.getProvidedClass());
                        if (description.isAnnotated()) {
                            bind.annotatedWith(description.getAnnotation()).toProvider(description);
                        } else {
                            bind.toProvider(description);
                        }
                    }
                }
            }
        }
    }
}