package org.maxur.theme.gray.config;

import org.maxur.commons.component.behavior.ThemeBehavior;
import org.maxur.commons.osgi.BaseGuiceActivator;
import org.maxur.commons.view.api.StyleBehavior;
import org.maxur.theme.gray.GrayThemeBehavior;

import static com.google.inject.name.Names.named;

/**
 * <p>GrayThemeActivator class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 08.05.12
 */
@SuppressWarnings("UnusedDeclaration")
public class GrayThemeActivator extends BaseGuiceActivator {

    public static final String PID = "org.maxur.theme.gray";

    public GrayThemeActivator() {
        super(PID);
    }

    @Override
    protected void config() {
        bind(StyleBehavior.class).single().annotatedWith(named("yaml_core")).toOSGiService();
        bind(StyleBehavior.class).single().annotatedWith(named("yaml_screen")).toOSGiService();
        bind(StyleBehavior.class).single().annotatedWith(named("yaml_navigation")).toOSGiService();
        bind(StyleBehavior.class).single().annotatedWith(named("yaml_printer")).toOSGiService();
        bind(StyleBehavior.class).single().annotatedWith(named("yaml_forms")).toOSGiService();
        export(new GrayThemeBehavior()).annotatedWith(named("yaml")).asOSGiService(ThemeBehavior.class);
    }
}


