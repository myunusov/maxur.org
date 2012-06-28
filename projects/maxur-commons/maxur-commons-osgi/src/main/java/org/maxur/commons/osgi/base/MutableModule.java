package org.maxur.commons.osgi.base;

import com.google.inject.AbstractModule;
import org.maxur.commons.core.api.BaseObservable;
import org.maxur.commons.core.api.Observable;
import org.maxur.commons.core.api.Observer;

/**
 * This is a Guice AbstractModule class with observable behaviour.
 * @see AbstractModule
 * @see Observable
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>6/8/12</pre>
 */
public abstract class MutableModule extends AbstractModule implements Observable {

    private Observable observable = new BaseObservable();

    /**
     * @see Observable#addObserver(org.maxur.commons.core.api.Observer)
     *
     * @param observer A Observer of Observable instance.
     */
    @Override
    public void addObserver(final Observer observer) {
        observable.addObserver(observer);
    }

    /**
     * @see Observable#removeObserver(org.maxur.commons.core.api.Observer)
     *
     * @param observer A Observer of Observable instance.
     */
    @Override
    public void removeObserver(final Observer observer) {
        observable.removeObserver(observer);
    }

    /**
     * @see org.maxur.commons.core.api.Observable#update()
     */
    @Override
    public void update() {
        observable.update();
    }

}
