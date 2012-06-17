package org.maxur.commons.osgi.base;

import com.google.inject.Injector;
import org.maxur.commons.core.api.AbstractRefresher;
import org.maxur.commons.core.api.Refresher;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim Yunusov
 * @version 1.0 13.05.12
 */
public final class MutableInjectorHolder {

    private static Map<String, InjectorBuilder> injectors = new HashMap<>();

    private MutableInjectorHolder() {
    }

    public static InjectorBuilder builder(final String pid) {
        return injectors.get(pid);
    }

    public static void start(final String pid) {
        injectors.put(pid, new InjectorBuilder());
    }

    public static void stop(final String pid) {
        injectors.remove(pid);
    }

    public static void addModule(final String pid, final MutableModule module) {
        builder(pid).addModule(module);
    }

    public static Refresher<Injector> refresher(final String pid) {
        return new InjectorRefresher(pid);
    }

    private static class InjectorRefresher extends AbstractRefresher<Injector> {

        private static final long serialVersionUID = -2613436504821318003L;

        private String pid;

        private transient Injector item;

        public InjectorRefresher(final String pid) {
            super();
            this.pid = pid;
        }

        @Override
        public boolean isStale() {
            return super.isStale() || this.item != MutableInjectorHolder.builder(pid).getResult();
        }

        @Override
        public Injector get() {
            if (isStale()) {
                this.item = MutableInjectorHolder.builder(pid).build();
            }
            return this.item;
        }

    }
}
