package org.maxur.commons.core.api;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * This is base implementation of Refresher interface.
 * @see Refresher
 *
 * @author Maxim Yunusov
 * @version 1.0 14.06.12
 */
public abstract class AbstractRefresher<I> implements Refresher<I> {

    private static final long serialVersionUID = -1246204405641707833L;

    private boolean isStale;

    public AbstractRefresher() {
        isStale = true;
    }

    @Override
    public boolean isStale() {
        return isStale;
    }

    /**
     * item is transient and must be set by deserialization
     *
     * @param stream ObjectInputStream
     * @throws java.io.IOException
     * @throws ClassNotFoundException
     */
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        isStale = true;
    }
}
