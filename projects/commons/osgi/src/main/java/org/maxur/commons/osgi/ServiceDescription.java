package org.maxur.commons.osgi;

import com.google.inject.Provider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import java.lang.annotation.Annotation;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author Maxim Yunusov
 * @version 1.0 27.05.12
 */
public class ServiceDescription implements Provider<Object> {

    private static final String ANNOTATION = "annotation";

    private Annotation annotation;

    private Object instance;

    private Class<?> type;

    private ServiceRegistration registration;

    private ServiceFactory factory;

    public static Builder builder() {
        return new Builder();
    }

    private ServiceDescription() {
    }

    /**
     * Returns true if provider mast be annotated.
     * @return true if provider mast be annotated
     */
    public boolean isAnnotated() {
        return annotation != null;
    }

    public Annotation getAnnotation() {
        return this.annotation;
    }

    public Object get() {
        return instance;
    }

    @Override
    public int hashCode() {
        return (null == factory ? instance : factory).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void register(final BundleContext bc) {
        final Dictionary<String, Object> properties;
        if (isAnnotated()) {
            properties = new Hashtable<>();
            properties.put(ANNOTATION, annotation);
        } else {
            properties = null;
        }
        registration = bc.registerService(type.getName(),
                null == factory ? instance : factory,
                properties);
    }

    public void unregister() {
        registration.unregister();
    }

    public static final class Builder {

        private final ServiceDescription serviceDescription;

        public Builder() {
            serviceDescription = new ServiceDescription();
        }

        public Builder reference(final ServiceReference reference) {
            this.serviceDescription.annotation =
                    (Annotation) reference.getProperty(ANNOTATION);
            return this;
        }

        public Builder instance(final Object instance) {
            this.serviceDescription.instance = instance;
            return this;
        }

        public Builder factory(final ServiceFactory factory) {
            this.serviceDescription.factory = factory;
            return this;
        }

        public Builder type(final Class<?> type) {
            this.serviceDescription.type = type;
            return this;
        }

        public Builder annotation(final Annotation annotation) {
            this.serviceDescription.annotation = annotation;
            return this;
        }

        public ServiceDescription build() {
            return serviceDescription;
        }

    }

}
