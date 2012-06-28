package org.maxur.commons.osgi.holder;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.maxur.commons.core.api.Observer;
import org.maxur.commons.core.api.Refreshable;
import org.maxur.commons.osgi.base.MutableModule;

import java.util.HashSet;
import java.util.Set;

/**
 * This class keeps and refreshes Guice Injector.
 * In any time injector can be updated by update parent injector, modules or module's contents.
 * This class  observes these events.
 *
 * @author Maxim Yunusov
 * @version 1.0 26.05.12
 */
public class InjectorHolder implements Observer, Refreshable {

    private Injector injector;

    private Injector parentInjector;

    private final Set<MutableModule> modules;

    private boolean isStale = true;

    /**
     * Must be called from InjectorHolderList only.
     */
    InjectorHolder() {
        modules = new HashSet<>();
    }

    /**
     * Set parent injector.
     *
     * @param injector The parent injector.
     */
    public void setParentInjector(final Injector injector) {
        this.parentInjector = injector;
        update();
    }

    /**
     * Add new module to injector.
     *
     * Injector must be refreshed on add new module or on update module.
     *
     * @param module A new guice configuration module.
     */
    public void addModule(final MutableModule module) {
        this.modules.add(module);
        module.addObserver(this);
        update();
    }


    /**
     * Lazy refresh of injector.
     *
     * @return A Guice Injector.
     */
    public Injector get() {
        if (this.isStale) {
            refresh();
        }
        return this.injector;
    }

    /**
     * Injector must be rebuild on update module or parent injector.
     * @see org.maxur.commons.core.api.Observer#update()
     */
    @Override
    public void update() {
        this.isStale = true;
    }

    /**
     * @see  org.maxur.commons.core.api.Refreshable#refresh()
     */
    @Override
    public void refresh() {
        this.injector = null != this.parentInjector ?
                this.parentInjector.createChildInjector(getModule()) :
                Guice.createInjector(getModule());
        this.isStale = false;
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