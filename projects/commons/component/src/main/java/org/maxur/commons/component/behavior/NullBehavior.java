package org.maxur.commons.component.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;

/**
 * This Class present 'NullObject' instance of abstract Behavior class.
 *
 * @author Maxim Yunusov
 * @version 1.0 13.05.12
 */
public final class NullBehavior extends Behavior {
    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = 3134098750502637787L;

    private static NullBehavior instance = new NullBehavior();

    /**
     * Returns the instance of NullBehavior (Gof singleton).
     */
    public static NullBehavior get() {
        return instance;
    }

    /**
     * This is private constructor must be called from static method get() only.
     */
    private NullBehavior() {
    }

    /**
     * @param component the component that has this behavior coupled.
     * @return always false this behavior must not be executed/rendered anywhere.
     * @see org.apache.wicket.behavior.Behavior#isEnabled(org.apache.wicket.Component).
     */
    @Override
    public boolean isEnabled(final Component component) {
        return false;
    }
}
