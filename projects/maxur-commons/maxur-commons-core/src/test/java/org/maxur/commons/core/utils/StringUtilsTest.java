package org.maxur.commons.core.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Maxim Yunusov
 * @version 1.0 30.05.12
 */
public class StringUtilsTest {



    @Test
    public void shouldBeBlankOnNull() throws Exception {
        assertFalse(StringUtils.isNotBlank(null));
    }

    @Test
    public void shouldBeBlankOnEmpty() throws Exception {
        assertFalse(StringUtils.isNotBlank(""));
    }

    @Test
    public void shouldBeReturnEmptyString() throws Exception {
        assertEquals("", StringUtils.emptyString());
    }

    @Test
    public void shouldBeBlankOnEmptyString() throws Exception {
        assertFalse(StringUtils.isNotBlank(StringUtils.emptyString()));
    }

    @Test
    public void shouldBeJoin() throws Exception {
        assertEquals("a b c", StringUtils.list("a", "b", "c").split(" ").join(" "));
    }

    @Test
    public void shouldBeJoinWithBlankElements() throws Exception {
        assertEquals("a,b,c", StringUtils.list("a", "", "b", "", "c", "").split(",").join(","));
    }

    @Test
    public void shouldBeJoinWithSpaceElements() throws Exception {
        assertEquals("a, ,b, ,c, ", StringUtils.list("a", " ", "b", " ", "c", " ").split(",").join(","));
    }


    @Test
    public void shouldBeJoinWithSeparator() throws Exception {
        assertEquals("a b c", StringUtils.list("a b", "c").split(" ").join(" "));
    }

    @Test
    public void shouldBeJoinWithStartAndTileSeparator() throws Exception {
        assertEquals("a b c", StringUtils.list(" a b ", "c").split(" ").join(" "));
    }

    @Test
    public void shouldBeJoinWithSeparatorOnly() throws Exception {
        assertEquals("", StringUtils.list("  ", " ").split(" ").join(" "));
    }

    @Test
    public void shouldBeJoinAsSeparator() throws Exception {
        assertEquals("s d", StringUtils.list("  ", "s d").split(" ").join(" "));
    }

    @Test
    public void testMerge() throws Exception {
        assertEquals("a b d f s", StringUtils.set("s f a b", "d", "f", "a b").split(" ").merge(" "));
    }


}
