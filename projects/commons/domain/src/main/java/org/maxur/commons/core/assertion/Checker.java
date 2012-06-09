package org.maxur.commons.core.assertion;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>6/9/12</pre>
 */
public class Checker extends AssertInstance {

    public Checker(final Object instance) {
        super(instance);
    }

    @Override
    protected Fail fail(final String message) {
        return new Fail();
    }
}
