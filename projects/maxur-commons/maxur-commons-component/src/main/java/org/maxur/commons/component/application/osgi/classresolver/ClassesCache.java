package org.maxur.commons.component.application.osgi.classresolver;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Usually class loaders implement more efficient caching strategies than we could possibly do,
 * but we experienced synchronization issue resulting in stack traces like:
 * java.lang.LinkageError: duplicate class definition:
 * <p/>
 * <pre>
 *    wicket/examples/repeater/RepeatingPage at java.lang.ClassLoader.defineClass1(Native Method)
 * </pre>
 * <p/>
 * This problem has gone since we synchronize the access.
 */
public class ClassesCache extends ClassResolver {

    private final ConcurrentHashMap<String, WeakReference<Class<?>>> classes = new ConcurrentHashMap<String, WeakReference<Class<?>>>();

    public Class<?> resolve(final String className) throws ClassNotFoundException {
        final WeakReference<Class<?>> reference = classes.get(className);
        return isNotGarbageCollected(reference) ?
            reference.get():
            addToCacheAndReturn(className, super.resolve(className));
    }

    private Class<?> addToCacheAndReturn(final String className, final Class<?> result) {
        classes.put(className, new WeakReference<Class<?>>(result));
        return result;
    }

    private boolean isNotGarbageCollected(WeakReference<Class<?>> reference) {
        return reference != null;
    }

}