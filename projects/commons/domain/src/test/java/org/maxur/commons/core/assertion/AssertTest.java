package org.maxur.commons.core.assertion;

import org.junit.Test;
import org.maxur.commons.core.api.SerializableArrayList;
import org.maxur.commons.core.api.SerializableList;

import static org.maxur.commons.core.assertion.Assert.argument;
import static org.maxur.commons.core.assertion.Assert.field;
import static org.maxur.commons.core.assertion.Assert.check;

/**
 * @author Maxim Yunusov
 * @version 1.0 06.06.12
 */
public class AssertTest {

    @Test
    public void testAssertIsInterface() throws Exception {
        argument(SerializableList.class).isInterface();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertIsInterfaceWithInvalidArgument() throws Exception {
        argument(Object.class).isInterface();
    }

    @Test
    public void testAssertIsInterfaceOf() throws Exception {
        argument(SerializableList.class).isInterfaceOf(new SerializableArrayList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertIsInterfaceOfWithInvalidArgument() throws Exception {
        argument(SerializableList.class).isInterfaceOf(new Object());
    }

    @Test
    public void testAssertNotNull() throws Exception {
        field(new Object()).notNull();
    }

    @Test(expected = IllegalStateException.class)
    public void testAssertNotNullWithNull() throws Exception {
        field(null).notNull();
    }

    @Test(expected = IllegalStateException.class)
    public void testCheckNotNullWithNull() throws Exception {
        check(null).notNull().ifNot(new IllegalStateException());
    }

    @Test
    public void testCheckNotNullWithNotNull() throws Exception {
        check(new Object()).notNull().ifNot(new IllegalStateException());
    }


}
