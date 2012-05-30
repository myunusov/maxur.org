package org.maxur.commons.component.utils;

import java.util.Iterator;

/**
 * @author Maxim Yunusov
 * @version 1.0 30.05.12
 */
public class OuterIterator<T> {

    private final Iterator<T> iterator;

    private T element;

    private OuterIterator(final Iterator<T> iterator) {
        this.iterator = iterator;
    }

    public static <T> OuterIterator<T> get(final Iterator<T> iterator) {
        return new OuterIterator<>(iterator);
    }

    public static <T> OuterIterator<T> get(final Iterable<T> iterable) {
        return new OuterIterator<>(iterable.iterator());
    }

    public T element() {
        return this.element;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public void next() {
        this.element = iterator.next();
    }


}
