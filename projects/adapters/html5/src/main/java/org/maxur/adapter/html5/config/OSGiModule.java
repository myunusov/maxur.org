package org.maxur.adapter.html5.config;

import org.maxur.adapter.html5.HTML5Behavior;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.view.api.JScriptBehavior;
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
        bind(export(JScriptBehavior.class)).toProvider(service(HTML5Behavior.class).export());
    }

}