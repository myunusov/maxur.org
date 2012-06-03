package org.maxur.commons.core.api;

import java.io.Serializable;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 11.05.12
 */
public interface SerializableList<E extends Serializable> extends List<E>,Serializable {
}
