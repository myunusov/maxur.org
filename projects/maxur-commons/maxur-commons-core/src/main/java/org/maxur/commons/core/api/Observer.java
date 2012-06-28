package org.maxur.commons.core.api;

/**
 * Interface of Observer.
 *
 * See Observer Gof pattern.
 *
 * @author Maxim Yunusov
 * @version 1.0 25.05.12
 */
public interface Observer {

    /**
     * Observable notify all observers of the event's occurrence,
     * in the form of an invocation of their 'update'.
     *
     * @see Observable
     *
     */
    void update();

}
