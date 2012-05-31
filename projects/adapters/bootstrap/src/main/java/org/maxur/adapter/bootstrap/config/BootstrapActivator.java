package org.maxur.adapter.bootstrap.config;

import org.maxur.adapter.bootstrap.BootstrapCoreBehavior;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.osgi.BaseGuiceActivator;
import org.maxur.commons.view.api.StyleBehavior;

import static com.google.inject.name.Names.named;

/**
 * @author Maxim Yunusov
 * @version 1.0 31.05.12
 */
@SuppressWarnings("UnusedDeclaration")
public class BootstrapActivator extends BaseGuiceActivator {

    public static final String PID = "org.maxur.adapter.bootstrap";

    public BootstrapActivator() {
        super(PID);
    }

    @Override
    protected void config() {
        bind(WebBrowserDetector.class).single().toOSGiService();
        export(new BootstrapCoreBehavior()).annotatedWith(named("bootstrap_core")).asOSGiService(StyleBehavior.class);
    }
}

