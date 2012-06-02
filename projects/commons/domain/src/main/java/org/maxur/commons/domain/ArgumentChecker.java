package org.maxur.commons.domain;

/**
 * @author Maxim Yunusov
 * @version 1.0 02.06.12
 */
public final class ArgumentChecker {

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
}
