package org.maxur.commons.view.config;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.04.12
 */
public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ApplicationModule());
        bindConstant().annotatedWith(Names.named("test.key")).to("test version");
    }
}
