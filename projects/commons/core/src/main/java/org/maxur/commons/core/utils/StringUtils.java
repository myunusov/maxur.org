package org.maxur.commons.core.utils;

import org.maxur.commons.core.api.OuterIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>StringUtils class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0
 */
public final class StringUtils {

    public static final String EMPTY_STRING = "";

    /**
     * <p>Private Constructor for StringUtils.</p>
     * <p/>
     * This StringUtils must not be constructed. It's Utils class.
     */
    private StringUtils() {
    }

    /**
     * Returns True if String Exist and not Blank.
     *
     * @param value The string for test.
     * @return True if String Exist and not Blank.
     */
    public static boolean isNotBlank(final String value) {
        return value != null && value.length() > 0;
    }

    public static String merge(final String separator, final String... strings) {
        final Set<String> result = new HashSet<>();
        for (String string : strings) {
            result.addAll(Arrays.asList(string.split(separator)));
        }
        return join(separator, result);
    }

    public static String join(final String separator, final String... strings) {
        final List<String> result = new ArrayList<>();
        for (String string : strings) {
            result.addAll(Arrays.asList(string.split(separator)));
        }
        return join(separator, result);
    }

    public static String join(final String separator, final Iterable<String> strings) {
        final OuterIterator<String> iterator = OuterIterator.get(strings);
        while (iterator.hasNext()) {
            iterator.next();
            if (isNotBlank(iterator.element())) {
                return addNext(separator, iterator);
            }
        }
        return EMPTY_STRING;
    }

    private static String addNext(final String separator, final OuterIterator<String> iterator) {
        final StringBuilder builder = new StringBuilder(iterator.element());
        for (;iterator.hasNext();iterator.next()) {
            if (isNotBlank(iterator.element())) {
                builder.append(separator).append(iterator.element());
            }
        }
        return builder.toString();
    }

    public static String emptyString() {
        return EMPTY_STRING;
    }
}
