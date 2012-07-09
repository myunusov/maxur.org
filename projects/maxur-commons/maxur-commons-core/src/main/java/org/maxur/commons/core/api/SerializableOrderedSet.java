package org.maxur.commons.core.api;

import java.io.Serializable;
import java.util.Collection;
import java.util.TreeSet;

/**
 * @author Maxim Yunusov
 * @version 1.0 11.05.12
 */
public class SerializableOrderedSet<E extends Serializable> extends TreeSet<E> implements SerializableSet<E> {

    private static final long serialVersionUID = -8140532191332903834L;

    public SerializableOrderedSet(final Collection<E> collection) {
        super(collection);
    }


    public SerializableOrderedSet() {
    }

}
