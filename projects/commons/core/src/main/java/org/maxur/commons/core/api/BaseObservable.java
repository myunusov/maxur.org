package org.maxur.commons.core.api;

import java.util.HashSet;
import java.util.Set;

/**
 * Base implementation of Observable interface.
 * @see Observable
 *
 * @author Maxim Yunusov
 * @version 1.0 26.05.12
 */
public class BaseObservable implements Observable {

    /**
     * Set of observers for this object.
     */
    private final Set<Observer> observers = new HashSet<>();

    /**
     * @see  Observable#addObserver(Observer).
     */
     @Override
    public final void addObserver(final Observer observer) {
        this.observers.add(observer);
    }

    /**
     * @see  Observable#removeObserver(Observer).
     */
    @Override
    public void removeObserver(final Observer observer) {
        this.observers.remove(observer);
    }

    /**
     * @see  Observable#update().
     */
    @Override
    public final void update() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
