package org.maxur.adapter.yaml.config;

import org.maxur.adapter.yaml.YamlBehavior;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.view.api.StyleBehavior;
import org.ops4j.peaberry.activation.util.PeaberryActivationModule;

import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.TypeLiterals.export;

/**
 * <p>OSGiModule class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 08.05.12
 */
@SuppressWarnings("UnusedDeclaration")
public class OSGiModule extends PeaberryActivationModule {

    /** {@inheritDoc} */
    @Override
    protected void configure() {
        bindService(WebBrowserDetector.class).single();
        bind(export(StyleBehavior.class)).toProvider(service(YamlBehavior.class).export());
    }

}