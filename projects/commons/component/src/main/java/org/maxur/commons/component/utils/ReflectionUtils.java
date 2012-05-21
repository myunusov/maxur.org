package org.maxur.commons.component.utils;

/**
 * Idea by Alex Tracer
 * see http://habrahabr.ru/post/66593/
 */
public final class ReflectionUtils {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private ReflectionUtils() {
    }

    public static Class getGenericParameterClass(
            final Class actualClass,
            final Class genericClass,
            final int parameterIndex
    ) {
        return new GenericParameters(actualClass, genericClass).get(parameterIndex);
    }

}