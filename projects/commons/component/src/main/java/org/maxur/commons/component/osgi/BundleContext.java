package org.maxur.commons.component.osgi;

import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Must be present in presentation bundle !
 * The Current Injector must be available on restart BundleContext's bundle.
 *
 * @author Maxim Yunusov
 * @version 1.0 13.05.12
 */
public final class BundleContext {

    private static ThreadLocal<BundleContext> threadLocal = new ThreadLocal<>();

    private static volatile Injector currentInjector;

    private final Injector injector;

    private BundleContext(final Injector injector) {
        this.injector = injector;
    }

    /**
     * <p>Setter for the field <code>injector</code>.</p>
     *
     * @param injector a {@link com.google.inject.Injector} object.
     */
    @Inject
    public static void setInjector(Injector injector) {
        BundleContext.currentInjector = injector;
    }

    /**
     * @return {@link Injector} bound to current thread
     */
    public static Injector getInjector() {
        return get() != null ? get().injector : currentInjector;
    }

    public static void persist() {
        BundleContext context = new BundleContext(currentInjector);
        threadLocal.set(context);
    }

    public static BundleContext get() {
        return threadLocal.get();
    }

}
