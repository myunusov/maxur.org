package org.maxur.commons.core.api;

import org.junit.Before;
import org.junit.Test;

import static org.maxur.commons.core.api.ArgumentChecker.assertIsInterface;
import static org.maxur.commons.core.api.ArgumentChecker.assertIsInterfaceOf;

/**
 * @author Maxim Yunusov
 * @version 1.0 06.06.12
 */
public class ArgumentCheckerTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAssertIsInterface() throws Exception {
        assertIsInterface(SerializableList.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertIsNotInterface() throws Exception {
        assertIsInterface(Object.class);
    }


    @Test
    public void testAssertIsInterfaceOf() throws Exception {
        assertIsInterfaceOf(SerializableList.class, new SerializableArrayList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertIsNotInterfaceOf() throws Exception {
        assertIsInterfaceOf(SerializableList.class, new Object());
    }
}
