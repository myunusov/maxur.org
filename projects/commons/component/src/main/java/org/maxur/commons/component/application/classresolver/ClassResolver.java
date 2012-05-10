package org.maxur.commons.component.application.classresolver;

/**
 * @author Maxim Yunusov
 * @version 1.0 10.05.12
 */
public abstract class ClassResolver {

    private ClassResolver next;

    public abstract Class<?> resolve(String className) throws ClassNotFoundException;

    public ClassResolver wrap(final ClassResolver wrappedClass) {
        next = wrappedClass;
        return wrappedClass;
    }

    protected Class<?> next(final String className) throws ClassNotFoundException {
        if (next == null) {
            throw new ClassNotFoundException(className);
        }
        return next.resolve(className);
    }

}
