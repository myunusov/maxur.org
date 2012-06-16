package org.maxur.commons.core.assertion;

/**
 * @author Maxim Yunusov
 * @version 1.0 09.06.12
 */
class AssertArgument extends AssertInstance {

    public AssertArgument(final Object instance) {
        super(instance);
    }

    @Override
    protected Fail fail(final String message) {
        throw new IllegalArgumentException(message);
    }

}
