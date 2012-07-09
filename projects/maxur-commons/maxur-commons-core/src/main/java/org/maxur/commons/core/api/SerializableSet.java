package org.maxur.commons.core.api;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Maxim Yunusov
 * @version 1.0 11.05.12
 */
public interface SerializableSet<E extends Serializable> extends Set<E>, Serializable {
}
