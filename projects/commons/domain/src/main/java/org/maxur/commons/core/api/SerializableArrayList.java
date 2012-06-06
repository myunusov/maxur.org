package org.maxur.commons.core.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 11.05.12
 */
public class SerializableArrayList<E extends Serializable> extends ArrayList<E> implements SerializableList<E> {

    private static final long serialVersionUID = -8140532191332903834L;

    public SerializableArrayList(final List<E> list) {
        super(list);
    }

    public SerializableArrayList() {
    }

}
