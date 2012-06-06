package org.maxur.commons.osgi;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import org.maxur.commons.core.api.Refresher;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maxim Yunusov
 * @version 1.0 13.05.12
 */
public final class MutableInjectorHolder {

    private static Map<String, MutableInjector> injectors = new HashMap<>();

    private MutableInjectorHolder() {
    }

    public static MutableInjector get(final String pid) {
        return injectors.get(pid);
    }

    public static void start(final String pid) {
        injectors.put(pid, new MutableInjector());
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
        return get(pid).refresher();
    }

}
