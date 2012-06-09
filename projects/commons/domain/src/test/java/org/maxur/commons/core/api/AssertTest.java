package org.maxur.commons.core.api;

import org.junit.Before;
import org.junit.Test;
import org.maxur.commons.core.assertion.Assert;

/**
 * @author Maxim Yunusov
 * @version 1.0 06.06.12
 */
public class AssertTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAssertIsInterface() throws Exception {
        Assert.argument(SerializableList.class).isInterface();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertIsInterfaceWithInvalidArgument() throws Exception {
        Assert.argument(Object.class).isInterface();
    }


    @Test
    public void testAssertIsInterfaceOf() throws Exception {
        Assert.argument(SerializableList.class).isInterfaceOf(new SerializableArrayList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertIsInterfaceOfWithInvalidArgument() throws Exception {
        Assert.argument(SerializableList.class).isInterfaceOf(new Object());
    }

    @Test
    public void testAssertNotNull() throws Exception {
        Assert.field(new Object()).notNull();
    }

    @Test(expected = IllegalStateException.class)
    public void testAssertNotNullWithNull() throws Exception {
        Assert.field(null).notNull();
    }


}
