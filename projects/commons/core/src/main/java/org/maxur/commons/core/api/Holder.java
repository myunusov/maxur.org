package org.maxur.commons.core.api;

import java.io.Serializable;

/**
 * This class represents refreshable provider
 * @see Refreshable
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>6/6/12</pre>
 */
public interface Holder<T> extends Serializable, Refreshable {

    T get();

}
