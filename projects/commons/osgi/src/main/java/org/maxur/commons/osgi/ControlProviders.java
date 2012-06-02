package org.maxur.commons.osgi;

import com.google.inject.AbstractModule;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.multibindings.Multibinder;
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

    private ControlProviders(final String pid) {
        this.pid = pid;
    }

    public static ControlProviders init(final String pid) {
        return new ControlProviders(pid);
    }

    public void addServiceManager(final BaseOSGiServiceManager manager) {
        managers.add(manager);
    }

    public ControlProviders start(final BundleContext bc) {
        for (OSGiServiceManager<?> manager : managers) {
            manager.start(bc, this.pid);
        }
        MutableInjectorHolder.addModule(this.pid, new ProvidersModule());
        return this;
    }

    public void stop() {
        for (OSGiServiceManager<?> manager : managers) {
            manager.stop();
        }
    }

    private final class ProvidersModule extends AbstractModule {
        @Override
        protected void configure() {
            for (OSGiServiceManager<?> manager : managers) {
                final Collection<ServiceDescription> descriptions = manager.getServiceDescriptions();
                if (manager.isMultiple()) {
                    final Multibinder binder;
                    if (manager.isAnnotated()) {
                        binder = Multibinder.newSetBinder(
                                binder(), manager.getProvidedClass(), manager.getAnnotation()
                        );
                    } else {
                        binder = Multibinder.newSetBinder(binder(), manager.getProvidedClass());
                    }
                    for (ServiceDescription description : descriptions) {
                        //noinspection unchecked
                        binder.addBinding().toProvider(description);
                    }
                } else {
                    for (ServiceDescription description : descriptions) {
                        @SuppressWarnings("unchecked")
                        final AnnotatedBindingBuilder<Object> bind =
                                (AnnotatedBindingBuilder<Object>) bind(manager.getProvidedClass());
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