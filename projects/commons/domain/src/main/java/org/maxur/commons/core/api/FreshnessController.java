package org.maxur.commons.core.api;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0 02.06.12
 */
public interface FreshnessController<T> extends Serializable {

    boolean isStale();

    T get();
}
