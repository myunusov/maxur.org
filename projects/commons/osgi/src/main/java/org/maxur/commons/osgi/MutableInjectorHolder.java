package org.maxur.commons.osgi;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import org.maxur.commons.core.api.BaseRefresher;
import org.maxur.commons.core.api.Holder;
import org.maxur.commons.core.api.Refresher;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maxim Yunusov
 * @version 1.0 13.05.12
 */
public final class MutableInjectorHolder {

    private static Map<String, InjectorBuilder> injectors = new HashMap<>();

    private MutableInjectorHolder() {
    }

    public static InjectorBuilder get(final String pid) {
        return injectors.get(pid);
    }

    public static void start(final String pid) {
        injectors.put(pid, new InjectorBuilder());
    }

    public static void stop(final String pid) {
        injectors.remove(pid);
    }

    public static void update(final String pid) {
        get(pid).update();
    }

    public static void addModule(final String pid, final AbstractModule module) {
        get(pid).addModule(module);
    }

    public static Refresher<Injector> refresher(final String pid) {
        return new BaseRefresher<>(new InjectorHolder(pid));
    }

    public static class InjectorHolder implements Holder<Injector> {

        private static final long serialVersionUID = -3954477844908111590L;

        private final String pid;

        public InjectorHolder(final String pid) {
            this.pid = pid;
        }

        @Override
        public Injector get() {
            return MutableInjectorHolder.get(pid).getResult();
        }

        @Override
        public void refresh() {
            MutableInjectorHolder.get(pid).build();

        }
    }

}
