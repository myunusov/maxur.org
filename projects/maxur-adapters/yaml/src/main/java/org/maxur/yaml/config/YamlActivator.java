package org.maxur.yaml.config;

import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.osgi.BaseGuiceActivator;
import org.maxur.commons.view.api.StyleBehavior;
import org.maxur.yaml.YamlBehavior;

import static com.google.inject.name.Names.named;

/**
 * @author Maxim Yunusov
 * @version 1.0 27.05.12
 */
@SuppressWarnings("UnusedDeclaration")
public class YamlActivator extends BaseGuiceActivator {

    public static final String PID = "org.maxur.adapter.yaml";

    public YamlActivator() {
        super(PID);
    }

    @Override
    protected void config() {
        bind(WebBrowserDetector.class).single().toOSGiService();
        export(new YamlBehavior()).annotatedWith(named("yaml")).asOSGiService(StyleBehavior.class);
    }
}
