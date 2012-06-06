package org.maxur.commons.core.api;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>6/6/12</pre>
 */
public interface Holder<T> extends Serializable {

    T get();

    void refresh();
}
