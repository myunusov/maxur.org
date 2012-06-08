package org.maxur.commons.osgi;

import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.multibindings.Multibinder;
import org.maxur.commons.core.api.Observer;

/**
 * @author Maxim Yunusov
 * @version 1.0 08.06.12
 */
final class ProvidersModule extends MutableModule implements Observer {

    private final ServiceDescriptions descriptions;

    ProvidersModule(final ServiceDescriptions descriptions) {
        this.descriptions = descriptions;
        this.descriptions.addObserver(this);
    }

    @Override
    protected void configure() {
        if (this.descriptions.isMultiple()) {
            bindMultipleProviders();
        } else {
            bindSingleProviders();
        }
    }

    @SuppressWarnings("unchecked")
    private void bindSingleProviders() {
        for (ServiceDescription description : this.descriptions) {
            final AnnotatedBindingBuilder<Object> bind =
                    (AnnotatedBindingBuilder<Object>) bind(this.descriptions.getProvidedClass());
            if (description.isAnnotated()) {
                bind.annotatedWith(description.getAnnotation());
            }
            bind.toProvider(description);
        }
    }

    @SuppressWarnings("unchecked")
    private void bindMultipleProviders() {
        final Multibinder binder;
        if (this.descriptions.isAnnotated()) {
            binder = Multibinder.newSetBinder(
                    binder(), this.descriptions.getProvidedClass(), this.descriptions.getAnnotation()
            );
        } else {
            binder = Multibinder.newSetBinder(binder(), this.descriptions.getProvidedClass());
        }
        for (ServiceDescription description : this.descriptions) {
            binder.addBinding().toProvider(description);
        }
    }

}
