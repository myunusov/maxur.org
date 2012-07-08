package org.maxur.commons.core.utils;

import org.maxur.commons.core.api.OuterIterator;

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

    public static StringList list(final String... strings) {
        return new StringList(strings);
    }

    public static StringSet set(final String... strings) {
        return new StringSet(strings);
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
        while (iterator.hasNext()) {
            iterator.next();
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
