package org.maxur.commons.osgi.providers;

import com.google.inject.Provider;
import org.maxur.commons.osgi.base.ServiceAnnotation;
import org.osgi.framework.ServiceReference;

import java.lang.annotation.Annotation;

/**
 * @author Maxim Yunusov
 * @version 1.0 13.06.12
 */
public final class ServiceProvider implements Provider<Object> {

    private Object instance;

    private ServiceAnnotation annotation;

    private ServiceProvider() {
    }

    @Override
    public Object get() {
        return instance;
    }

    @Override
    public int hashCode() {
        return instance.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ServiceProvider) {
            final ServiceProvider service = (ServiceProvider) obj;
            return (instance.equals(service.instance));
        } else {
            return super.equals(obj);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean isAnnotated() {
        return annotation.isAnnotated();
    }

    public Annotation getAnnotation() {
        return annotation.getAnnotation();
    }

    public static final class Builder {

        private Object instance;

        private Annotation annotation;

        public Builder instance(final Object instance) {
            this.instance = instance;
            return this;
        }

        public Builder reference(final ServiceReference reference) {
            annotation = (Annotation) reference.getProperty(ServiceAnnotation.ANNOTATION);
            return this;
        }

        public ServiceProvider build() {
            final ServiceProvider result = new ServiceProvider();
            result.instance = this.instance;
            result.annotation = new ServiceAnnotation(annotation);
            return result;
        }
    }

}
