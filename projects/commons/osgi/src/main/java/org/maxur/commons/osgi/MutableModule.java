package org.maxur.commons.osgi;

import com.google.inject.AbstractModule;
import org.maxur.commons.core.api.BaseObservable;
import org.maxur.commons.core.api.Observable;
import org.maxur.commons.core.api.Observer;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>6/8/12</pre>
 */
public abstract class MutableModule extends AbstractModule implements Observable {

    private Observable observable = new BaseObservable();

    @Override
    public void addObserver(final Observer observer) {
        observable.addObserver(observer);
    }

    @Override
    public void removeObserver(final Observer observer) {
        observable.removeObserver(observer);
    }

    @Override
    public void update() {
        observable.update();
    }

}
