package org.maxur.commons.core.utils;

import org.maxur.commons.core.api.SerializableArrayList;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Maxim Yunusov
 * @version 1.0 08.07.12
 */
public class StringList extends SerializableArrayList<String> {

    private static final long serialVersionUID = 8840447530088129967L;

    public StringList(final String... strings) {
        Collections.addAll(this, strings);
    }

    public final StringList split(final String separator) {
        final StringList result = new StringList();
        for (String string : this) {
            result.addAll(Arrays.asList(string.split(separator)));
        }
        return result;
    }

    public String join(final String separator) {
        return StringUtils.join(separator, this);
    }


}
