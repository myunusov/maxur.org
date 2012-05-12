package org.maxur.commons.component.behavior;

import org.apache.wicket.behavior.Behavior;

/**
 * @author Maxim Yunusov
 * @version 1.0 11.05.12
 */
public class BaseThemeBehavior extends CompositeBehavior implements ThemeBehavior {

    private static final long serialVersionUID = -6567719420886502433L;

    public BaseThemeBehavior(Behavior... behaviors) {
        super(behaviors);
    }

    @Override
    public Behavior asBehavior() {
        return this;
    }
}
