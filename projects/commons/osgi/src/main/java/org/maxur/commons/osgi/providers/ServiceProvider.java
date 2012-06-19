package org.maxur.commons.osgi.providers;

import com.google.inject.Provider;
import org.maxur.commons.osgi.base.AbstractService;
import org.osgi.framework.ServiceReference;

import java.lang.annotation.Annotation;

/**
 * @author Maxim Yunusov
 * @version 1.0 13.06.12
 */
public final class ServiceProvider extends AbstractService implements Provider<Object> {

    private Object instance;

    private ServiceProvider() {
        super();
    }

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

    public static final class Builder {

        private final ServiceProvider result;

        public Builder() {
            result = new ServiceProvider();
        }

        public Builder instance(final Object instance) {
            this.result.instance = instance;
            return this;
        }

        public Builder reference(final ServiceReference reference) {
            this.result.setAnnotation((Annotation) reference.getProperty(ANNOTATION));
            return this;
        }

        public ServiceProvider build() {
            return result;
        }
    }

}
