package org.maxur.commons.component.osgi;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    private final Set<OSGiObserver> observers = new HashSet<>();

    private static GuiceModuleHolder get(final String pid) {
        if (null == holders.get(pid)) {
            holders.put(pid, new GuiceModuleHolder());
        }
        return holders.get(pid);
    }

    private GuiceModuleHolder() {
    }

    public static void inject(final String pid, final Object subject) {
        get(pid).inject(subject);
    }

    public static Injector getInjector(final String pid) {
        return get(pid).getInjector();
    }

    public static void update(final String pid) {
        get(pid).update();
    }

    public static void addObserver(final String pid, final OSGiObserver observer) {
        get(pid).addObserver(observer);
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

    private Injector getInjector() {
        if (this.injector == null) {
            this.injector = this.makeInjector();
        }
        return injector;
    }

    private void addObserver(final OSGiObserver observer) {
        this.observers.add(observer);
    }

    private void update() {
        this.injector = null;
        for (OSGiObserver observer : observers) {
            observer.update();
        }
    }

    private void inject(final Object subject) {
        this.getInjector().injectMembers(subject);
    }

    private Injector makeInjector() {
        return this.webInjector == null ?
                Guice.createInjector(this.propertiesModule, this.providersModule) :
                this.webInjector.createChildInjector(this.propertiesModule, this.providersModule);
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
