package org.maxur.commons.component.osgi;

import java.util.ArrayList;
import java.util.Collection;

public class Lister<T> {
    final Collection<T> lister = Collections.synchronizedCollection(new ArrayList<T>());

    public Lister() {
    }
}