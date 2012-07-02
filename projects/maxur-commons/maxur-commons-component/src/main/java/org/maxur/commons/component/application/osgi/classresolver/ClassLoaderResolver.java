package org.maxur.commons.component.application.osgi.classresolver;

import org.apache.wicket.WicketRuntimeException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
                    return Class.forName(className, false, loader);
                } catch (ClassNotFoundException ignore) {
                }
            }
            throw new ClassNotFoundException(className);
        }
    }

    public Iterator<URL> getResources(final String name) {
        final LoadedFiles loadedFiles = new LoadedFiles(name);
        try {
            for (ClassLoader loader : classLoaders) {
                loadedFiles.addFilesFromClassLoader(loader);
            }
        }
        catch (IOException e) {
            throw new WicketRuntimeException(e);
        }
        return loadedFiles.iterator();
    }


    private static class LoadedFiles implements Iterable<URL> {

        private final List<URL> loadedFiles = new ArrayList<>();

        private final String name;

        public LoadedFiles(final String name) {
            this.name = name;
        }

        public void addFilesFromClassLoader(final ClassLoader classLoader) throws IOException {
            final Enumeration<URL> resources = classLoader.getResources(name);
            if (resources != null) {
                while (resources.hasMoreElements()) {
                    final URL url = resources.nextElement();
                    if (!loadedFiles.contains(url)) {
                        loadedFiles.add(url);
                    }
                }
            }
        }

        @Override
        public Iterator<URL> iterator() {
            return loadedFiles.iterator();
        }
    }

}