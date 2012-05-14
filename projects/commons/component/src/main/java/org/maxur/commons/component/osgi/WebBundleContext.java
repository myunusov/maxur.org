package org.maxur.commons.component.osgi;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.apache.wicket.behavior.Behavior;
import org.maxur.commons.component.behavior.NullBehavior;
import org.maxur.commons.component.behavior.ThemeBehavior;
import org.maxur.commons.component.model.webclient.BaseWebBrowserDetector;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;

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

    private final Dictionary properties;

    private static Injector webInjector;


    private WebBundleContext() {
        this.properties = currentProperties;
    }

    public static Injector getWebInjector() {
        return webInjector;
    }

    public static void setWebInjector(Injector webInjector) {
        WebBundleContext.webInjector = webInjector;
    }

    public static Injector getInjector() {
        final Injector injector = webInjector == null ?
                Guice.createInjector(WebBundleContext.getBundleModule()) :
                webInjector.createChildInjector(WebBundleContext.getBundleModule());
        return injector;
    }

    public static Iterable<? extends Module> getBundleModule() {
        Collection<Module> result = new ArrayList<>();
        result.add(getOSGiModule());
        result.add(getPropertiesModule());
        return result;
    }

    public static Module getOSGiModule() {
        return new AbstractModule() {
            @Override
            protected void configure() {
                bind(WebBrowserDetector.class).to(BaseWebBrowserDetector.class); // TODO STUB
                bind(ThemeBehavior.class).to(MyThemeBehavior.class); // TODO STUB
            }
        };
    }

    private static Module getPropertiesModule() {
        return new AbstractModule() {
            @Override
            protected void configure() {
                final Dictionary properties = getProperties();
                final Enumeration keys = properties.keys();
                while (keys.hasMoreElements()) {
                    final String key = keys.nextElement().toString();
                    final String value = properties.get(key).toString();
                    bindConstant().annotatedWith(named(key)).to(value);
                }
            }
        };
    }

    /**
     * @return {@link Dictionary} bound to current thread
     */
    public static Dictionary getProperties() {
        return get() != null ? get().properties : currentProperties;
    }

    public static void setProperties(final Dictionary properties) {
        WebBundleContext.currentProperties = properties;
    }


    public static void persist() {
        WebBundleContext context = new WebBundleContext();
        threadLocal.set(context);
    }

    public static WebBundleContext get() {
        return threadLocal.get();
    }


    private static class MyThemeBehavior implements ThemeBehavior { // TODO FAKE
        private static final long serialVersionUID = -731747328777429822L;

        @Override
        public Behavior asBehavior() {
            return NullBehavior.get();
        }


    }
}
