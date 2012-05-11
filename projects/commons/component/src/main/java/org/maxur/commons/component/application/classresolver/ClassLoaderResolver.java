package org.maxur.commons.component.application.classresolver;

import java.util.Collection;
import java.util.HashSet;

public class ClassLoaderResolver extends ClassResolver {

    private final Collection<ClassLoader> classLoaders = new HashSet<>();

    public void addClassLoader(final ClassLoader classLoader) {
        classLoaders.add(classLoader);
    }

    @Override
    public Class<?> resolve(String className) throws ClassNotFoundException {
        synchronized (this) {
            // see http://njbartlett.name/2010/08/30/osgi-readiness-loading-classes.html
            for (ClassLoader loader : classLoaders) {
                try {
                    // see http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6500212
                    // clazz = loader.loadClass(className);/
                    final Class<?> result = Class.forName(className, false, loader);
                    if (result != null) {
                        return result;
                    }
                } catch (ClassNotFoundException ignore) {
                }
            }
            return null;
        }
    }
}