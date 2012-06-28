package org.maxur.commons.component.application.classresolver;

import org.apache.wicket.Application;
import org.apache.wicket.application.IClassResolver;

import java.net.URL;
import java.util.Iterator;

/**
 *
 * see http://hwellmann.blogspot.com/2011/06/wicket-and-osgi.html
 * see https://github.com/wicketstuff/core/wiki/Osgi
 *
 * @author Maxim Yunusov
 * @version 1.0 09.05.12
 */
public class OsgiClassResolver implements IClassResolver {

    private final ClassResolver resolver;

    public OsgiClassResolver() {
        resolver = new ClassesCache()
                .wrap(new PrimitivesTypeMap())
                .wrap(new ClassLoaderResolver());
        resolver.addClassLoaders(
                getWicketBundleClassLoader(),
                getApplicationBundleClassLoader(),
                getContextClassLoader());
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private ClassLoader getApplicationBundleClassLoader() {
        return Application.get().getClass().getClassLoader();
    }

    private ClassLoader getWicketBundleClassLoader() {
        return Application.class.getClassLoader();
    }


    /**
     * @see org.apache.wicket.application.IClassResolver#resolveClass(java.lang.String)
     */
    public final Class<?> resolveClass(final String className) throws ClassNotFoundException {
        return resolver.resolve(className);
    }

    /**
     * Tries to load all the resources by the name that is given.
     *
     * @see org.apache.wicket.application.IClassResolver#getResources(java.lang.String)
     */
    public Iterator<URL> getResources(final String name) {
        return resolver.getResources(name);
    }


    public void addClassLoader(final ClassLoader classLoader) {
        resolver.addClassLoader(classLoader);
    }


}
