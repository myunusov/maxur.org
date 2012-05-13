package org.maxur.theme.blue;

import com.google.inject.Inject;
import org.apache.wicket.behavior.Behavior;
import org.maxur.commons.component.behavior.CompositeBehavior;
import org.maxur.commons.component.behavior.ThemeBehavior;
import org.maxur.commons.view.api.StyleBehavior;

/**
 * @author Maxim Yunusov
 * @version 1.0 11.05.12
 */
public class MaxurThemeBehavior extends CompositeBehavior implements ThemeBehavior {

    private static final long serialVersionUID = -939857534940713313L;

    private static StyleBehavior styleBehavior;

    @Inject
    public static void setStyleBehavior(StyleBehavior styleBehavior) {
        MaxurThemeBehavior.styleBehavior = styleBehavior;
    }

    public MaxurThemeBehavior() {
        super(styleBehavior.asBehavior(), new BlueThemeBehavior());
    }

    @Override
    public Behavior asBehavior() {
        return this;
    }
}
