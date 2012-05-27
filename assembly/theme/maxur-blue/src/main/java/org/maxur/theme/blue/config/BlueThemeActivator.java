package org.maxur.theme.blue.config;

import org.maxur.commons.component.behavior.ThemeBehavior;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.osgi.BaseGuiceActivator;
import org.maxur.commons.view.api.StyleBehavior;
import org.maxur.theme.blue.BlueThemeBehavior;

import static com.google.inject.name.Names.named;

/**
 * <p>BlueThemeActivator class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 08.05.12
 */
@SuppressWarnings("UnusedDeclaration")
public class BlueThemeActivator extends BaseGuiceActivator {

    public static final String PID = "org.maxur.theme.blue";

    public BlueThemeActivator() {
        super(PID);
    }

    @Override
    protected void config() {
        bind(WebBrowserDetector.class).single().toOSGiService();
        bind(StyleBehavior.class).single().toOSGiService();
        export(new BlueThemeBehavior()).annotatedWith(named("blue_theme")).asOSGiService(ThemeBehavior.class);
    }
}