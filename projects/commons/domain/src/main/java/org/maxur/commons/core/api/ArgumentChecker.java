package org.maxur.commons.core.api;

/**
 * @author Maxim Yunusov
 * @version 1.0 02.06.12
 */
public final class ArgumentChecker {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private ArgumentChecker() {
    }

    public static void assertIsInterface(final Class<?> interfaceClass) {
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException(interfaceClass.getName() + " is not interface");
        }
    }

    public static void assertIsInterfaceOf(final Class<?> interfaceClass, final Object object) {
        if (!interfaceClass.isInstance(object)) {
            throw new IllegalArgumentException(
                    interfaceClass.getName() +
                            " is not interface of " +
                            object.getClass().getName());
        }
    }

    public static void assertNotNull(final Object field) {
        if (null == field) {
            throw new IllegalStateException("Some field is null");
        }
    }

}
