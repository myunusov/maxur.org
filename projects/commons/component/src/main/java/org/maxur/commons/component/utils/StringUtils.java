package org.maxur.commons.component.utils;

/**
 * <p>StringUtils class.</p>
 *
 * @author maxim
 * @version $Id: $
 */
public final class StringUtils {

    /**
     * <p>Private Constructor for StringUtils.</p>
     *
     * This StringUtils must not be constructed. It's Utils class.
     *
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

}
