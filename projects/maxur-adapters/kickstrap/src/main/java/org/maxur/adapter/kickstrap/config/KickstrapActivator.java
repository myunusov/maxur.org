package org.maxur.adapter.kickstrap.config;

import org.maxur.adapter.kickstrap.KickstrapCoreBehavior;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.osgi.BaseGuiceActivator;
import org.maxur.commons.view.api.StyleBehavior;

import static com.google.inject.name.Names.named;

/**
 * @author Maxim Yunusov
 * @version 1.0 31.05.12
 */
@SuppressWarnings("UnusedDeclaration")
public class KickstrapActivator extends BaseGuiceActivator {

    public static final String PID = "org.maxur.adapter.bootstrap";

    public KickstrapActivator() {
        super(PID);
    }

    @Override
    protected void config() {
        bind(WebBrowserDetector.class).single().toOSGiService();
        export(new KickstrapCoreBehavior()).annotatedWith(named("kickstrap")).asOSGiService(StyleBehavior.class);
    }
}

