package org.maxur.commons.core.api;

import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;

import static org.maxur.commons.core.api.ArgumentChecker.assertIsInterfaceOf;

/**
 * @author Maxim Yunusov
 * @version 1.0 06.06.12
 */
public class SerializableArrayListTest {

    @Test
    public void shouldBeSerializable() throws Exception {
        final SerializableArrayList<Serializable> list = new SerializableArrayList<Serializable>();
        assertIsInterfaceOf(Serializable.class, list);
    }

    @Test
    public void shouldBeSerializableWithListArgument() throws Exception {
        final SerializableArrayList<Serializable> list = new SerializableArrayList<>(new ArrayList<Serializable>());
        assertIsInterfaceOf(Serializable.class, list);
    }

}
