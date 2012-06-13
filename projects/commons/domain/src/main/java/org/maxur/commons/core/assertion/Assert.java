package org.maxur.commons.core.assertion;

/**
 * @author Maxim Yunusov
 * @version 1.0 02.06.12
 */
public final class Assert {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private Assert() {
    }

    public static AssertClass argument(final Class<?> aClass) {
        return new AssertArgument(aClass);
    }

    public static AssertValue field(final Object field) {
        return new AssertField(field);
    }

    public static AssertValue check(final Object field) {
        return new Checker(field);
    }

    public static RuntimeException error(final String message) {
        return new IllegalStateException(message);
    }

}
