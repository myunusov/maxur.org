package org.maxur.commons.core.assertion;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>6/9/12</pre>
 */
final class Success implements Result {

    private Success() {
    }

    public static Success get() {
        return new Success();
    }

    @Override
    public void then(final RuntimeException error) {
    }

}
