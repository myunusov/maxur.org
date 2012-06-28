package org.maxur.adapter.yaml4.config;

import org.maxur.adapter.yaml4.YamlCoreBehavior;
import org.maxur.adapter.yaml4.YamlFormsBehavior;
import org.maxur.adapter.yaml4.YamlNavigationBehavior;
import org.maxur.adapter.yaml4.YamlPrinterBehavior;
import org.maxur.adapter.yaml4.YamlScreenLayoutBehavior;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.osgi.BaseGuiceActivator;
import org.maxur.commons.view.api.StyleBehavior;

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
        export(new YamlCoreBehavior()).annotatedWith(named("yaml_core")).asOSGiService(StyleBehavior.class);
        export(new YamlScreenLayoutBehavior()).annotatedWith(named("yaml_screen")).asOSGiService(StyleBehavior.class);
        export(new YamlNavigationBehavior()).annotatedWith(named("yaml_navigation")).asOSGiService(StyleBehavior.class);
        export(new YamlPrinterBehavior()).annotatedWith(named("yaml_printer")).asOSGiService(StyleBehavior.class);
        export(new YamlFormsBehavior()).annotatedWith(named("yaml_forms")).asOSGiService(StyleBehavior.class);
    }
}
