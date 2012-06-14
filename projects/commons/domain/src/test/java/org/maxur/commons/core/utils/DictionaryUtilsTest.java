package org.maxur.commons.core.utils;

import org.junit.Test;

import java.util.Dictionary;

import static org.junit.Assert.assertEquals;

/**
 * @author Maxim Yunusov
 * @version 1.0 14.06.12
 */
public class DictionaryUtilsTest {

    @Test
    public void testSingleton() throws Exception {
        final Dictionary<String,String> dictionary = DictionaryUtils.singleton("key", "value");
        assertEquals(1, dictionary.size());
        assertEquals("key", dictionary.keys().nextElement());
        assertEquals("value", dictionary.get("key"));
    }


}
