package org.maxur.commons.view;

import org.apache.wicket.Application;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.application.IClassResolver;
import org.maxur.commons.view.classresolver.ClassLoaderResolver;
import org.maxur.commons.view.classresolver.ClassResolver;
import org.maxur.commons.view.classresolver.ClassesCache;
import org.maxur.commons.view.classresolver.PrimitivesTypeMap;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * see http://hwellmann.blogspot.com/2011/06/wicket-and-osgi.html
 * see https://github.com/wicketstuff/core/wiki/Osgi
 *
 * @author Maxim Yunusov
 * @version 1.0 09.05.12
 */
public class OsgiClassResolver implements IClassResolver {

    private final ClassLoaderResolver classLoaderResolver = new ClassLoaderResolver();

    private final ClassResolver resolver = new ClassesCache();

    public OsgiClassResolver() {
        resolver.wrap(new PrimitivesTypeMap()).wrap(classLoaderResolver);
    }

    /**
     * @see org.apache.wicket.application.IClassResolver#resolveClass(java.lang.String)
     */
    public final Class<?> resolveClass(final String className) throws ClassNotFoundException {
        return resolver.resolve(className);
    }

    public void addClassLoader(final ClassLoader classLoader) {
        classLoaderResolver.addClassLoader(classLoader);
    }

    /**
     *
     * @see org.apache.wicket.application.IClassResolver#getResources(java.lang.String)
     */
    public Iterator<URL> getResources(String name) {
        HashSet<URL> loadedFiles = new HashSet<URL>();
        try {
            // Try the classloader for the wicket jar/bundle
            Enumeration<URL> resources = Application.class.getClassLoader().getResources(name);
            loadResources(resources, loadedFiles);

            // Try the classloader for the user's application jar/bundle
            resources = Application.get().getClass().getClassLoader().getResources(name);
            loadResources(resources, loadedFiles);

            // Try the context class loader
            resources = Thread.currentThread().getContextClassLoader().getResources(name);
            loadResources(resources, loadedFiles);
        }
        catch (IOException e) {
            throw new WicketRuntimeException(e);
        }

        return loadedFiles.iterator();
    }

    /**
     *
     * @param resources
     * @param loadedFiles
     */
    private void loadResources(Enumeration<URL> resources, Set<URL> loadedFiles) {
        if (resources != null) {
            while (resources.hasMoreElements()) {
                final URL url = resources.nextElement();
                if (!loadedFiles.contains(url)) {
                    loadedFiles.add(url);
                }
            }
        }
    }

}
