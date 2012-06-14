package org.maxur.commons.core.assertion;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>6/9/12</pre>
 */
class Fail implements Result {

    Fail() {
    }

    @Override
    public void onFailThrow(final RuntimeException error) {
        throw error;
    }

}
