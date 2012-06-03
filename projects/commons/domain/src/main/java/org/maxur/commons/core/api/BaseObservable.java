package org.maxur.commons.core.api;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.05.12
 */
public class BaseObservable implements Observable {

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
     * @see  Observable#notifyObservers().
     *
     * Template method (with hooks).
     */
    @Override
    public final void notifyObservers() {
        beforeUpdate();
        for (Observer observer : observers) {
            observer.update();
        }
        afterUpdate();
    }

    /**
     * Hook on after Update.
     */
    protected void afterUpdate() {
    }

    /**
     * Hook on before Update.
     */
    protected void beforeUpdate() {
    }

}
