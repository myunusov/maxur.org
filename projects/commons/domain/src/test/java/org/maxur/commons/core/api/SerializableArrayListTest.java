package org.maxur.commons.core.api;

import org.junit.Test;
import org.maxur.commons.core.assertion.Assert;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Maxim Yunusov
 * @version 1.0 06.06.12
 */
public class SerializableArrayListTest {

    @Test
    public void shouldBeSerializable() throws Exception {
        final SerializableArrayList<Serializable> list = new SerializableArrayList<Serializable>();
        Assert.argument(Serializable.class).isInterfaceOf(list);
    }

    @Test
    public void shouldBeSerializableWithListArgument() throws Exception {
        final SerializableArrayList<Serializable> list = new SerializableArrayList<>(new ArrayList<Serializable>());
        Assert.argument(Serializable.class).isInterfaceOf(list);
    }

}
