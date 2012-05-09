package org.maxur.commons.component.config;

import org.maxur.commons.component.model.webclient.BaseWebBrowserDetector;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.ops4j.peaberry.activation.util.PeaberryActivationModule;

import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.TypeLiterals.export;

/**
 * <p>OSGiModule class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 26.04.12
 */
@SuppressWarnings("UnusedDeclaration")
public class OSGiModule extends PeaberryActivationModule {

    /** {@inheritDoc} */
    @Override
    protected void configure() {
        bind(export(WebBrowserDetector.class)).toProvider(service(BaseWebBrowserDetector.class).export());
    }

}
