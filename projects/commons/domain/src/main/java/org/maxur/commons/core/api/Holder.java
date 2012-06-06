package org.maxur.commons.core.api;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>6/6/12</pre>
 */
public interface Holder<T> {

    T get();

    void refresh();
}
