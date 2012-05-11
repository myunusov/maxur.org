package org.maxur.commons.component.utils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Maxim Yunusov
 * @version 1.0 11.05.12
 */
public class SerializableArrayList<E extends Serializable> extends ArrayList<E> implements  SerializableList<E> {

    private static final long serialVersionUID = -8140532191332903834L;

}
