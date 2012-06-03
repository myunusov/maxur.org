package org.maxur.commons.core.api;

/**
* @author Maxim Yunusov
* @version 1.0 02.06.12
*/
public abstract class AbstractFreshnessController<T> implements Observer, FreshnessController<T> {

    private static final long serialVersionUID = -6562206165620000361L;

    private boolean isStale;

    private T item;

    protected AbstractFreshnessController(final T item) {
        this.item = item;
    }

    @Override
    public boolean isStale() {
        return this.isStale;
    }

    @Override
    public T get() {
        if (this.isStale) {
            this.item = refresh();
            this.isStale = false;
        }
        return this.item;
    }

    protected abstract T refresh();

    @Override
    public void update() {
        this.isStale = true;
    }
}
