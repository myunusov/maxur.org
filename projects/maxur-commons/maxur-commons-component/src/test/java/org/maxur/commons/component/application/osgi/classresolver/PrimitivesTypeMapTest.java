package org.maxur.commons.component.application.osgi.classresolver;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Maxim Yunusov
 * @version 1.0 19.05.12
 */
public class PrimitivesTypeMapTest {

    private PrimitivesTypeMap typeMap;

    @Before
    public void setUp() throws Exception {
        typeMap = new PrimitivesTypeMap();
    }

    @Test
    public void shouldReturnByte() throws Exception {
        assertEquals(byte.class, typeMap.resolve("byte"));
    }

    @Test
    public void shouldReturnShort() throws Exception {
        assertEquals(short.class, typeMap.resolve("short"));
    }

    @Test
    public void shouldReturnInt() throws Exception {
        assertEquals(int.class, typeMap.resolve("int"));
    }

    @Test
    public void shouldReturnLong() throws Exception {
        assertEquals(long.class, typeMap.resolve("long"));
    }

    @Test
    public void shouldReturnFloat() throws Exception {
        assertEquals(float.class, typeMap.resolve("float"));
    }

    @Test
    public void shouldReturnDouble() throws Exception {
        assertEquals(double.class, typeMap.resolve("double"));
    }

    @Test
    public void shouldReturnBoolean() throws Exception {
        assertEquals(boolean.class, typeMap.resolve("boolean"));
    }

    @Test
    public void shouldReturnChar() throws Exception {
        assertEquals(char.class, typeMap.resolve("char"));
    }

    @Test(expected = ClassNotFoundException.class)
    public void shouldBeReturnExceptionOnCallWithoutNextResolver() throws Exception {
        typeMap.resolve("test1");
    }


}
