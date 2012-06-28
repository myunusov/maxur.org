package org.maxur.commons.core.utils;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author Maxim Yunusov
 * @version 1.0 13.06.12
 */
public final class DictionaryUtils {

    private DictionaryUtils() {
    }

    public static <K,V> Dictionary<K,V> singleton(final K key, final V value) {
        final Dictionary<K, V> result = new Hashtable<>();
        result.put(key, value);
        return result;
    }

}
