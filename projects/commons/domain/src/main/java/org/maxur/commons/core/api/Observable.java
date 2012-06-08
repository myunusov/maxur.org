package org.maxur.commons.core.api;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.05.12
 */
public interface Observable {

    /**
     * Adds new observer.
     * @param observer A Observer of Observable instance.
     */
    void addObserver(Observer observer);

    /**
     * Remove the observer.
      * @param observer A Observer of Observable instance.
     */
    void removeObserver(Observer observer);

    /**
     * Observable notify all observers of the event's occurrence,
     * in the form of an invocation of their 'update'.
     *
     * @see Observer
     *
     */
    void update();

}
