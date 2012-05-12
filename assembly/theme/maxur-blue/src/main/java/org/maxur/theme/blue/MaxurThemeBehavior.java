package org.maxur.theme.blue;

import org.apache.wicket.behavior.Behavior;
import org.maxur.commons.component.behavior.CompositeBehavior;
import org.maxur.commons.component.behavior.ThemeBehavior;

/**
 * @author Maxim Yunusov
 * @version 1.0 11.05.12
 */
public class MaxurThemeBehavior extends CompositeBehavior implements ThemeBehavior {

    private static final long serialVersionUID = -939857534940713313L;

    public MaxurThemeBehavior() {
        super(new BlueThemeBehavior());
    }

    @Override
    public Behavior asBehavior() {
        return this;
    }
}
