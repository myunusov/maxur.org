package org.maxur.commons.view;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.maxur.commons.view.config.ApplicationModule;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.04.12
 */
public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ApplicationModule());
        bindConstant().annotatedWith(Names.named("version")).to("test version");
    }
}
