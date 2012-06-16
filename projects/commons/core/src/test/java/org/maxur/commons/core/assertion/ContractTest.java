package org.maxur.commons.core.assertion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.commons.core.api.SerializableArrayList;
import org.maxur.commons.core.api.SerializableList;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.maxur.commons.core.assertion.Contract.argument;
import static org.maxur.commons.core.assertion.Contract.check;
import static org.maxur.commons.core.assertion.Contract.error;
import static org.maxur.commons.core.assertion.Contract.field;

/**
 * @author Maxim Yunusov
 * @version 1.0 06.06.12
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractTest {

    @Mock
    private Logger logger;

    @Test
    public void testFieldIsClass() throws Exception {
        field(Object.class).isClass();
    }

    @Test
    public void testArgumentIsClass() throws Exception {
        argument(Object.class).isClass();
    }

    @Test
    public void testCheckIsClass() throws Exception {
        check(Object.class).isClass();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testArgumentIsNotClass() throws Exception {
        argument(new Object()).isClass();
    }

    @Test(expected = IllegalStateException.class)
    public void testCheckIsNotClass() throws Exception {
        check(new Object()).isClass().onFailThrow(new IllegalStateException());
    }


    @Test
    public void testArgumentIsInterface() throws Exception {
        argument(SerializableList.class).isInterface();
    }

    @Test
    public void testCheckIsInterface() throws Exception {
        check(SerializableList.class).isInterface().onFailThrow(new IllegalStateException());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testArgumentIsInterfaceWithInvalidArgument() throws Exception {
        argument(Object.class).isInterface();
    }

    @Test(expected = IllegalStateException.class)
    public void testCheckIsInterfaceWithInvalidArgument() throws Exception {
        check(Object.class).isInterface().onFailThrow(new IllegalStateException());
    }


    @Test
    public void testArgumentIsInterfaceOf() throws Exception {
        argument(SerializableList.class).isInterfaceOf(new SerializableArrayList());
    }

    @Test
    public void testCheckIsInterfaceOf() throws Exception {
        check(SerializableList.class)
                .isInterfaceOf(new SerializableArrayList()).onFailThrow(new IllegalStateException());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testArgumentIsInterfaceOfWithInvalidArgument() throws Exception {
        argument(SerializableList.class).isInterfaceOf(new Object());
    }

    @Test(expected = IllegalStateException.class)
    public void testCheckIsInterfaceOfWithInvalidArgument() throws Exception {
        check(SerializableList.class).isInterfaceOf(new Object()).onFailThrow(new IllegalStateException());
    }


    @Test
    public void testFieldNotNullWithNotNull() throws Exception {
        field(new Object()).notNull();
    }

    @Test
    public void testCheckNotNullWithNotNull() throws Exception {
        check(new Object()).notNull().onFailThrow(new IllegalStateException());
    }

    @Test(expected = IllegalStateException.class)
    public void testFieldNotNullWithNull() throws Exception {
        Object o = null;
        field(o).notNull();
    }

    @Test(expected = IllegalStateException.class)
    public void testCheckNotNullWithNull() throws Exception {
        Object o = null;
        check(o).notNull().onFailThrow(new IllegalStateException());
    }

    @Test
    public void testError() throws Exception {
        final RuntimeException error = error("Message");
        assertTrue(error instanceof IllegalStateException);
        assertEquals("Message", error.getMessage());
    }

    @Test
    public void testErrorMessage() throws Exception {
        new FakeChecker(new Object()).isClass();
        verify(logger).error("java.lang.Object is not class");
    }

    public class FakeChecker extends AssertInstance {

        public FakeChecker(final Object instance) {
            super(instance);
        }

        @Override
        protected Fail fail(final String message) {
            logger.error(message);
            return null;
        }
    }

}
