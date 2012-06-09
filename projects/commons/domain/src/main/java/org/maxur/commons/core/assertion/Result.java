package org.maxur.commons.core.assertion;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>6/9/12</pre>
 */
public interface Result {
    void then(RuntimeException error);
}
