package org.maxur.commons.osgi;

import org.maxur.commons.core.api.BaseObservable;
import org.osgi.framework.ServiceReference;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class ServiceDescriptions extends BaseObservable implements Iterable<ServiceDescription> {

    final Map<ServiceReference, ServiceDescription> serviceDescriptions = new HashMap<>();

    public void clear() {
        serviceDescriptions.clear();
        update();
    }

    public void remove(final ServiceReference reference) {
        serviceDescriptions.remove(reference);
        update();
    }

    public ServiceDescription put(final ServiceReference reference, final ServiceDescription description) {
        update();
        return serviceDescriptions.put(reference, description);
    }

    @Override
    public Iterator<ServiceDescription> iterator() {
        return serviceDescriptions.values().iterator();
    }

}