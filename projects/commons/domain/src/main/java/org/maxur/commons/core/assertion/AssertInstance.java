package org.maxur.commons.core.assertion;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>6/9/12</pre>
 */
abstract class AssertInstance implements AssertValue, AssertClass {

    protected final Object instance;

    public AssertInstance(final Object instance) {
        this.instance = instance;
    }

    @Override
    public final Result notNull() {
        return null != this.instance ? success() : fail("Some field is null");
    }

    @Override
    public final Result isClass() {
        return instance instanceof Class ? success() : fail(className() + " is not class");
    }

    @Override
    public final Result isInterface() {
        return asClass().isInterface() ? success() : fail(className() + " is not interface");
    }

    @Override
    public final Result isInterfaceOf(final Object object) {
        return (asClass().isInterface() && asClass().isInstance(object)) ?
                success() :
                fail(className() + " is not interface of " + object.getClass().getName());
    }

    private Class asClass() {
        isClass();
        return (Class) this.instance;
    }

    private String className() {
        return instance.getClass().getName();
    }

    protected abstract Fail fail(String message);

    protected Success success() {
        return Success.get();
    }



}
