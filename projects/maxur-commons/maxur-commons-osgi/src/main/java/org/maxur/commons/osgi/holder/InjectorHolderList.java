package org.maxur.commons.osgi.holder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim Yunusov
 * @version 1.0 13.05.12
 */
public final class InjectorHolderList {

    private static Map<String, InjectorHolder> holders = new HashMap<>();

    private InjectorHolderList() {
    }

    public static InjectorHolder holder(final String pid) {
        return holders.get(pid);
    }

    public static void start(final String pid) {
        holders.put(pid, new InjectorHolder());
    }

    public static void stop(final String pid) {
        holders.remove(pid);
    }

}
