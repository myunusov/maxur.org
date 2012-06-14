package org.maxur.commons.core.assertion;

/**
 * @author Maxim Yunusov
 * @version 1.0 02.06.12
 */
public final class Contract {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private Contract() {
    }

    public static AssertClass argument(final Class<?> argument) {
        return new AssertArgument(argument);
    }

    public static AssertValue argument(final Object argument) {
        return new AssertArgument(argument);
    }

    public static AssertClass field(final Class<?> field) {
        return new AssertField(field);
    }

    public static AssertValue field(final Object field) {
        return new AssertField(field);
    }

    public static AssertClass check(final Class<?> field) {
        return new Checker(field);
    }

    public static AssertValue check(final Object field) {
        return new Checker(field);
    }

    public static RuntimeException error(final String message) {
        return new IllegalStateException(message);
    }

}
