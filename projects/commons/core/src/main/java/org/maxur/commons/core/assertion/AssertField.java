package org.maxur.commons.core.assertion;

/**
 * @author Maxim Yunusov
 * @version 1.0 09.06.12
 */
class AssertField extends AssertInstance {

    public AssertField(final Object instance) {
        super(instance);
    }

    @Override
    protected Fail fail(final String message) {
        throw new IllegalStateException(message);
    }

}
