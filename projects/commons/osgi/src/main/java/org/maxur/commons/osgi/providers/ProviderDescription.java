package org.maxur.commons.osgi.providers;

import com.google.inject.Provider;
import org.maxur.commons.osgi.base.AbstractServiceDescription;
import org.osgi.framework.ServiceReference;

import java.lang.annotation.Annotation;

/**
 * @author Maxim Yunusov
 * @version 1.0 13.06.12
 */
public final class ProviderDescription extends AbstractServiceDescription implements Provider<Object> {

    private Object instance;

    private ProviderDescription() {
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
        if (obj instanceof ProviderDescription) {
            final ProviderDescription description = (ProviderDescription) obj;
            return (instance.equals(description.instance));
        } else {
            return super.equals(obj);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private final ProviderDescription result;

        public Builder() {
            result = new ProviderDescription();
        }

        public Builder instance(final Object instance) {
            this.result.instance = instance;
            return this;
        }

        public Builder reference(final ServiceReference reference) {
            this.result.setAnnotation((Annotation) reference.getProperty(ANNOTATION));
            return this;
        }

        public ProviderDescription build() {
            return result;
        }
    }

}
