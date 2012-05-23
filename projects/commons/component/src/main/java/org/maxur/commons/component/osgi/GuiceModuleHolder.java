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

    private static ThreadLocal<GuiceModuleHolder> threadLocal = new ThreadLocal<>();

    private static Injector webInjector;

    private static Map<String, AbstractModule> providersModules = new HashMap<>();

    private static Map<String, AbstractModule> propertiesModules = new HashMap<>();

    private final Injector injector;

    private GuiceModuleHolder(final String pid) {
        injector = makeInjector(pid);
    }

    public static Injector getInjector(final String pid) {
        return get() != null ? get().injector : makeInjector(pid);
    }

    private static Injector makeInjector(final String pid) {
        return webInjector == null ?
                    Guice.createInjector(propertiesModules.get(pid), providersModules.get(pid)) :
                    webInjector.createChildInjector(propertiesModules.get(pid), providersModules.get(pid));
    }

    public static void setWebInjector(Injector webInjector) {
        GuiceModuleHolder.webInjector = webInjector;
    }

    public static void setPropertiesModule(final String pid, final AbstractModule module) {
        GuiceModuleHolder.propertiesModules.put(pid, module);
    }

    public static void setProvidersModule(final String pid, final AbstractModule module) {
        GuiceModuleHolder.providersModules.put(pid, module);
    }

    public static void persist(final String pid) {
        GuiceModuleHolder context = new GuiceModuleHolder(pid);
        threadLocal.set(context);
    }

    public static GuiceModuleHolder get() {
        return threadLocal.get();
    }

}
