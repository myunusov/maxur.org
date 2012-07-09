package org.maxur.commons.core.utils;

import org.maxur.commons.core.api.SerializableOrderedSet;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Maxim Yunusov
 * @version 1.0 08.07.12
 */
public class StringSet extends SerializableOrderedSet<String> {

    private static final long serialVersionUID = 8840447530088129967L;

    public StringSet(final String... strings) {
        Collections.addAll(this, strings);
    }

    public final StringSet split(final String separator) {
        final StringSet result = new StringSet();
        for (String string : this) {
            result.addAll(Arrays.asList(string.split(separator)));
        }
        return result;
    }

    public String merge(final String separator) {
        return StringUtils.join(separator, this);
    }

}
