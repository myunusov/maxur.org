package org.maxur.commons.osgi.base;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.maxur.commons.core.api.Observer;
import org.maxur.commons.osgi.base.MutableModule;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.05.12
 */
public class InjectorBuilder implements Observer {

    private Injector injector;

    private Injector parentInjector;

    private final Set<MutableModule> modules;

    /**
     * Must be called from MutableInjectorHolder only.
     */
    InjectorBuilder() {
        modules = new HashSet<>();
    }

    public void setParentInjector(final Injector injector) {
        this.parentInjector = injector;
        update();
    }

    public void addModule(final MutableModule module) {
        this.modules.add(module);
        module.addObserver(this);
    }

    /**
     * Injector must be rebuild on update module.
     */
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
            build();
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
                for (AbstractModule module : modules) {
                    install(module);
                }
            }
        };
    }

}