package org.maxur.taskun.war.config;

import org.maxur.commons.component.behavior.ThemeBehavior;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.osgi.BaseGuiceActivator;
import org.maxur.taskun.domain.IssueLister;

import static com.google.inject.name.Names.named;

/**
 * Extension of the default OSGi bundle activator.
 *
 * @author Maxim Yunusov
 * @version 1.0 13.05.12
 *
 */
@SuppressWarnings("UnusedDeclaration")
public final class BaseTaskunActivator extends BaseGuiceActivator {

    public static final String PID = "org.maxur.taskun";

    public BaseTaskunActivator() {
        super(PID);
    }

    @Override
    protected void config() {
        bind(ThemeBehavior.class).annotatedWith(named("blue_theme")).toOSGiService();
        bind(IssueLister.class).toOSGiService();
        bind(WebBrowserDetector.class).toOSGiService();
    }


}