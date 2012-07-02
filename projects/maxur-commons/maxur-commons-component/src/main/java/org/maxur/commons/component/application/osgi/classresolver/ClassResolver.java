package org.maxur.commons.component.application.osgi.classresolver;

import org.apache.wicket.WicketRuntimeException;

import java.net.URL;
import java.util.Iterator;

/**
 * @author Maxim Yunusov
 * @version 1.0 10.05.12
 */
public abstract class ClassResolver {

    private ClassResolver next;

    protected Class<?> resolve(final String className) throws ClassNotFoundException {
        if (next == null) {
            throw new ClassNotFoundException(className);
        }
        return next.resolve(className);
    }

    public Iterator<URL> getResources(final String name) {
        if (next == null) {
            throw new WicketRuntimeException("Resource '" + name + "' is not found");
        }
        return next.getResources(name);
    }

    public ClassResolver wrap(final ClassResolver wrappedClass) {
        next = wrappedClass;
        return wrappedClass;
    }


    protected void addClassLoaders(final ClassLoader... loaders) {
        for (ClassLoader loader : loaders) {
            addClassLoader(loader);
        }
    }

    protected void addClassLoader(final ClassLoader classLoader) {
        if (next != null) {
            next.addClassLoader(classLoader);
        }
    }

}
