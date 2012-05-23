package org.maxur.commons.component.osgi;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.HashMap;
import java.util.Map;

/**
 * Must be present in presentation bundle !
 * The Current Injector must be available on restart GuiceModuleHolder's bundle.
 *
 * @author Maxim Yunusov
 * @version 1.0 13.05.12
 */
public final class GuiceModuleHolder {

    private static Map<String, GuiceModuleHolder> holders = new HashMap<>();

    private Injector injector;

    private Injector webInjector;

    private AbstractModule providersModule;

    private AbstractModule propertiesModule;

    private static GuiceModuleHolder get(final String pid) {
        if (null == holders.get(pid)) {
            holders.put(pid, new GuiceModuleHolder());
        }
        return holders.get(pid);
    }

    private GuiceModuleHolder() {
    }

    public static Injector getInjector(final String pid) {
        if (get(pid).injector == null) {
            get(pid).injector = makeInjector(pid);
        }
        return get(pid).injector;
    }

    private static Injector makeInjector(final String pid) {
        return get(pid).webInjector == null ?
                Guice.createInjector(get(pid).propertiesModule, get(pid).providersModule) :
                get(pid).webInjector.createChildInjector(get(pid).propertiesModule, get(pid).providersModule);
    }

    public static void setWebInjector(final String pid, final Injector webInjector) {
        get(pid).setWebInjector(webInjector);
    }

    public static void setPropertiesModule(final String pid, final AbstractModule module) {
        get(pid).setPropertiesModule(module);
    }

    public static void setProvidersModule(final String pid, final AbstractModule module) {
        get(pid).setProvidersModule(module);
    }

    public void setWebInjector(final Injector webInjector) {
        this.webInjector = webInjector;
        this.injector = null;
    }

    public void setProvidersModule(final AbstractModule providersModule) {
        this.providersModule = providersModule;
        this.injector = null;
    }

    public void setPropertiesModule(final AbstractModule propertiesModule) {
        this.propertiesModule = propertiesModule;
        this.injector = null;
    }
}
