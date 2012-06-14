package org.maxur.commons.core.assertion;

/**
 * @author Maxim Yunusov
 * @version 1.0 09.06.12
 */
public interface AssertClass {

    Result isInterface();

    Result isInterfaceOf(Object object);

    Result isClass();
}
