package org.maxur.commons.component.osgi;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import static com.google.inject.name.Names.named;

/**
 * Must be present in presentation bundle !
 * The Current Injector must be available on restart WebBundleContext's bundle.
 *
 * @author Maxim Yunusov
 * @version 1.0 13.05.12
 */
public final class WebBundleContext {

    private static ThreadLocal<WebBundleContext> threadLocal = new ThreadLocal<>();

    private static Dictionary currentProperties = new Hashtable();

    private static OSGiServiceProvider<?>[] currentServiceProviders;

    private static Injector webInjector;

    private final OSGiServiceProvider<?>[] serviceProviders;

    private final Dictionary properties;

    private WebBundleContext() {
        this.properties = currentProperties;
        this.serviceProviders = currentServiceProviders;
    }

    public static Injector getWebInjector() {
        return webInjector;
    }

    public static void setWebInjector(Injector webInjector) {
        WebBundleContext.webInjector = webInjector;
    }

    public static Injector getInjector() {
        return webInjector == null ?
                Guice.createInjector(WebBundleContext.getBundleModule()) :
                webInjector.createChildInjector(WebBundleContext.getBundleModule());
    }

    public static void setProperties(final Dictionary properties) {
        WebBundleContext.currentProperties = properties;
    }

    /**
     * @return {@link Dictionary} bound to current thread
     */
    private static Dictionary getProperties() {
        return get() != null ? get().properties : currentProperties;
    }

    public static void setProviders(OSGiServiceProvider<?>... serviceProviders) {
        WebBundleContext.currentServiceProviders = serviceProviders;
    }

    /**
     * @return {@link OSGiServiceProvider[]} bound to current thread
     */
    private static OSGiServiceProvider<?>[] getProviders() {
        return get() != null ? get().serviceProviders : currentServiceProviders;
    }

    public static Iterable<? extends Module> getBundleModule() {
        Collection<Module> result = new ArrayList<>();
        result.add(getPropertiesModule());
        result.add(getProvidersModule());
        return result;
    }

    private static Module getPropertiesModule() {
        return new AbstractModule() {
            @Override
            protected void configure() {
                final Dictionary dictionary = getProperties();
                final Enumeration keys = dictionary.keys();
                while (keys.hasMoreElements()) {
                    final String key = keys.nextElement().toString();
                    final String value = dictionary.get(key).toString();
                    bindConstant().annotatedWith(named(key)).to(value);
                }

            }
        };
    }

    public static Module getProvidersModule() {
        return new AbstractModule() {
            @Override
            protected void configure() {
                for (OSGiServiceProvider<?> provider : getProviders()) {
                    //noinspection unchecked
                    bind(provider.getProvidedClass()).toProvider(provider);
                }
            }
        };
    }

    public static void persist() {
        WebBundleContext context = new WebBundleContext();
        threadLocal.set(context);
    }

    public static WebBundleContext get() {
        return threadLocal.get();
    }

}
