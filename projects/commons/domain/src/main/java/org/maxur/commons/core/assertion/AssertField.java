package org.maxur.commons.core.assertion;

/**
 * @author Maxim Yunusov
 * @version 1.0 09.06.12
 */
class AssertField implements AssertValue {

    private final Object field;

    public AssertField(final Object field) {
        this.field = field;
    }

    @Override
    public void notNull() {
        if (null == field) {
            throw new IllegalStateException("Some field is null");
        }
    }
}
