package org.maxur.commons.core.api;

import java.io.Serializable;

/**
 * This interface to provide the capability to refresh other (refreshable) object.
 *
 * @author Maxim Yunusov
 * @version 1.0 02.06.12
 */
public interface Refresher<T> extends Serializable {

    boolean isStale();

    T get();
}
