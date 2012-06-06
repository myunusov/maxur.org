package org.maxur.commons.osgi;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.05.12
 */
public class InjectorBuilder {

    private Injector injector;

    private Injector parentInjector;

    private final Map<Class<? extends AbstractModule>, AbstractModule> modules = new HashMap<>();

    /**
     * Must be called from MutableInjectorHolder only.
     */
    InjectorBuilder() {
    }

    public void setParentInjector(final Injector injector) {
        this.parentInjector = injector;
        update();
    }

    public void addModule(final AbstractModule module) {
        this.modules.put(module.getClass(), module);
        update();
    }

    public void update() {
        this.injector = null;
    }

    public void build() {
        this.injector = this.makeInjector();
    }

    /**
     * Lazy initialization of injector.
     *
     * @return A Guice Injector.
     */
    public Injector getResult() {
        if (null == this.injector) {
            this.injector = this.makeInjector();
        }
        return injector;
    }

    private Injector makeInjector() {
        return null != this.parentInjector ?
                this.parentInjector.createChildInjector(getModule()) :
                Guice.createInjector(getModule());
    }

    private Module getModule() {
        return new AbstractModule() {
            @Override
            protected void configure() {
                for (AbstractModule module : modules.values()) {
                    install(module);
                }
            }
        };
    }

}