package org.maxur.commons.osgi.base;

import org.junit.Test;

import static com.google.inject.name.Names.named;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Maxim Yunusov
 * @version 1.0 20.06.12
 */
public class ServiceAnnotationTest {


    @Test
    public void shouldNotBeAnnotatedOnInit() throws Exception {
        final ServiceAnnotation serviceAnnotation = new ServiceAnnotation();
        assertFalse(serviceAnnotation.isAnnotated());
    }

    @Test
    public void shouldBeAnnotatedOnSetAnnotation() throws Exception {
        final ServiceAnnotation serviceAnnotation = new ServiceAnnotation(named(""));
        assertTrue(serviceAnnotation.isAnnotated());
    }

    @Test
    public void shouldBeReturnAnnotationOnSetAnnotation() throws Exception {
        final ServiceAnnotation serviceAnnotation = new ServiceAnnotation(named("a"));
        assertEquals(named("a"), serviceAnnotation.getAnnotation());
    }
}
