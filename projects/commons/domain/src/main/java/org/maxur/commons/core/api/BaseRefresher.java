package org.maxur.commons.core.api;



/**
* @author Maxim Yunusov
* @version 1.0 02.06.12
*/
public class BaseRefresher<T> implements Refresher<T> {

    private static final long serialVersionUID = -6562206165620000361L;

    private final Holder<T> itemHolder;

    private T item;

    public BaseRefresher(final Holder<T> itemHolder) {
        this.itemHolder = itemHolder;
        this.item = itemHolder.get();
    }

    @Override
    public boolean isStale() {
        return this.item != this.itemHolder.get();
    }

    @Override
    public T get() {
        if (isStale()) {
            this.itemHolder.refresh();
            this.item = this.itemHolder.get();
        }
        return this.item;
    }

}
