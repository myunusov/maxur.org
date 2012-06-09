package org.maxur.commons.core.assertion;

/**
 * @author Maxim Yunusov
 * @version 1.0 09.06.12
 */
class AssertArgument implements AssertClass{

    private final Class<?> aClass;

    public AssertArgument(final Class<?> aClass) {
        this.aClass = aClass;
    }

    @Override
    public void isInterface() {
        if (!aClass.isInterface()) {
            throw new IllegalArgumentException(aClass.getName() + " is not interface");
        }
    }

    @Override
    public void isInterfaceOf(final Object object) {
        isInterface();
        if (!aClass.isInstance(object)) {
            throw new IllegalArgumentException(
                    aClass.getName() + " is not interface of " + object.getClass().getName()
            );
        }
    }

}
