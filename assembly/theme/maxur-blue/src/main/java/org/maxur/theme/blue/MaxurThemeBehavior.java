package org.maxur.theme.blue;

import com.google.inject.Inject;
import org.maxur.commons.component.behavior.BaseThemeBehavior;
import org.maxur.commons.view.api.StyleBehavior;

/**
 * @author Maxim Yunusov
 * @version 1.0 11.05.12
 */
public class MaxurThemeBehavior extends BaseThemeBehavior {

    private static final long serialVersionUID = -939857534940713313L;

    private static StyleBehavior styleBehavior;

    @Inject
    public static void setStyleBehavior(StyleBehavior styleBehavior) {
        MaxurThemeBehavior.styleBehavior = styleBehavior;
    }

    public MaxurThemeBehavior() {
        super(styleBehavior.asBehavior(), new BlueThemeBehavior());
    }

}
