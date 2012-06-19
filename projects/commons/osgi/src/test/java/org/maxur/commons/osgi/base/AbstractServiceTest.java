package org.maxur.commons.osgi.base;

import org.junit.Before;
import org.junit.Test;

import static com.google.inject.name.Names.named;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Maxim Yunusov
 * @version 1.0 20.06.12
 */
public class AbstractServiceTest {

    private AbstractService service;

    @Before
    public void setUp() throws Exception {
        service = new AbstractService() {};
    }

    @Test
    public void shouldNotBeAnnotatedOnInit() throws Exception {
        assertFalse(service.isAnnotated());
    }

    @Test
    public void shouldBeAnnotatedOnSetAnnotation() throws Exception {
        service.setAnnotation(named(""));
        assertTrue(service.isAnnotated());
    }

    @Test
    public void shouldBeReturnAnnotationOnSetAnnotation() throws Exception {
        service.setAnnotation(named("a"));
        assertEquals(named("a"), service.getAnnotation());
    }
}
