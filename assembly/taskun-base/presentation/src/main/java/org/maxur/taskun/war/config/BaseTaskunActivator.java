package org.maxur.taskun.war.config;

import org.maxur.commons.component.behavior.ThemeBehavior;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.component.osgi.BaseGuiceActivator;
import org.maxur.taskun.domain.IssueLister;

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
        bindSingle(ThemeBehavior.class);
        bindSingle(IssueLister.class);
        bindSingle(WebBrowserDetector.class);
    }

}