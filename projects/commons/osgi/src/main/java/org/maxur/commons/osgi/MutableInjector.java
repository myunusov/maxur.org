package org.maxur.commons.osgi;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.maxur.commons.domain.BaseObservable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.05.12
 */
public class MutableInjector extends BaseObservable {

    private Injector injector;

    private Injector parentInjector;

    private final Map<Class<? extends AbstractModule>, AbstractModule> modules = new HashMap<>();

    /**
     * Must be called from MutableInjectorHolder only.
     */
    MutableInjector() {
    }

    public void inject(final Object subject) {
        this.getInjector().injectMembers(subject);
    }

    /**
     * Lazy initialization of injector.
     *
     * @return A Guice Injector.
     */
    public Injector getInjector() {
        if (null == this.injector) {
            this.injector = this.makeInjector();
        }
        return injector;
    }

    public Injector makeInjector() {
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
        notifyObservers();
    }

}