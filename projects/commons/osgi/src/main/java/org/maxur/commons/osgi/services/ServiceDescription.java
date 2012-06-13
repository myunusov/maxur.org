package org.maxur.commons.osgi.services;

import org.maxur.commons.core.utils.DictionaryUtils;
import org.maxur.commons.osgi.base.AbstractServiceDescription;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import java.lang.annotation.Annotation;
import java.util.Dictionary;

/**
 * @author Maxim Yunusov
 * @version 1.0 13.06.12
 */
public final class ServiceDescription extends AbstractServiceDescription {

    private Class<?> type;

    private ServiceRegistration registration;

    private ServiceFactory factory;

    private ServiceDescription() {
        super();
    }

    public void register(final BundleContext bc) {
        final Dictionary<String, Annotation> properties =
                isAnnotated() ?
                        DictionaryUtils.singleton(ANNOTATION, getAnnotation()) :
                        null;
        registration = bc.registerService(type.getName(), get(), properties);
    }

    public void unregister() {
        registration.unregister();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int hashCode() {
        return factory.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ServiceDescription) {
            final ServiceDescription description = (ServiceDescription) obj;
            return factory.equals(description.factory);
        } else {
            return super.equals(obj);
        }
    }

    protected Object get() {
        return factory;
    }

    public static final class Builder {

        private final ServiceDescription result;

        public Builder() {
            result = new ServiceDescription();
        }

        public Builder type(final Class<?> type) {
            this.result.type = type;
            return this;
        }

        public Builder factory(final ServiceFactory factory) {
            this.result.factory = factory;
            return this;
        }

        public Builder annotation(final Annotation annotation) {
            this.result.setAnnotation(annotation);
            return this;
        }

        public ServiceDescription build() {
            return result;
        }
    }

}
