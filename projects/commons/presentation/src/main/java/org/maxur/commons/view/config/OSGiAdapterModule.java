package org.maxur.commons.view.config;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * @author Maxim Yunusov
 * @version 1.0 04.05.12
 */
public class OSGiAdapterModule extends AbstractModule {

    private static Injector injector;

    @Inject
    public static void setInjector(Injector injector) {
        OSGiAdapterModule.injector = injector;
    }

    @Override
    protected void configure() {

    }

    @Provides
    @Named("version")
    public String provideVersionNumber() {
        return injector.getInstance(Key.get(String.class, Names.named(OSGiModule.VERSION_KEY)));
    }

}
