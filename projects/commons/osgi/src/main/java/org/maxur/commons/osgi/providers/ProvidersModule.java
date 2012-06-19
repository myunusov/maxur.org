package org.maxur.commons.osgi.providers;

import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.multibindings.Multibinder;
import org.maxur.commons.core.api.Observer;
import org.maxur.commons.osgi.base.MutableModule;

/**
 * @author Maxim Yunusov
 * @version 1.0 08.06.12
 */
final class ProvidersModule extends MutableModule implements Observer {

    private final ProvidersGroup group;

    ProvidersModule(final ProvidersGroup group) {
        this.group = group;
        this.group.addObserver(this);
    }

    @Override
    protected void configure() {
        if (this.group.isMultiple()) {
            bindMultipleProviders();
        } else {
            bindSingleProviders();
        }
    }

    @SuppressWarnings("unchecked")
    private void bindSingleProviders() {
        for (ServiceProvider service : this.group) {
            final AnnotatedBindingBuilder<Object> bind =
                    (AnnotatedBindingBuilder<Object>) bind(this.group.getProvidedClass());
            if (service.isAnnotated()) {
                bind.annotatedWith(service.getAnnotation());
            }
            bind.toProvider(service);
        }
    }

    @SuppressWarnings("unchecked")
    private void bindMultipleProviders() {
        final Multibinder binder;
        if (this.group.isAnnotated()) {
            binder = Multibinder.newSetBinder(
                    binder(), this.group.getProvidedClass(), this.group.getAnnotation()
            );
        } else {
            binder = Multibinder.newSetBinder(binder(), this.group.getProvidedClass());
        }
        for (ServiceProvider service : this.group) {
            binder.addBinding().toProvider(service);
        }
    }

}
