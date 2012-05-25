package org.maxur.commons.component.osgi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Lister<T> {

    private final Collection<T> providers = Collections.synchronizedCollection(new ArrayList<T>());

    public Lister() {
    }

    public boolean bind(T provider) {
        return providers.add(provider);
    }

    public boolean unbind(T provider) {
        return providers.remove(provider);
    }

    public Collection<T> getProviders() {
        return providers;
    }
}