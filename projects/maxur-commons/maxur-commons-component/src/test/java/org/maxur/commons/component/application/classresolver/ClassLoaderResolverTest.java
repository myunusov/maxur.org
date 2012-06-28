package org.maxur.commons.component.application.classresolver;

import org.apache.wicket.WicketRuntimeException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Maxim Yunusov
 * @version 1.0 20.05.12
 */
@RunWith(MockitoJUnitRunner.class)
public class ClassLoaderResolverTest {

    @Mock
    private ClassLoader loader;

    private ClassLoaderResolver resolver;

    @Before
    public void setUp() throws Exception {
        resolver = new ClassLoaderResolver();
    }

    @Test
    public void shouldBeReturnClassByName() throws Exception {
        resolver.addClassLoader(this.getClass().getClassLoader());
        assertEquals(ClassLoaderResolverTest.class,
                resolver.resolve("org.maxur.commons.component.application.classresolver.ClassLoaderResolverTest"));
    }

    @Test(expected = ClassNotFoundException.class)
    public void shouldBeReturnExceptionWithoutClassLoader() throws Exception {
        assertEquals(ClassLoaderResolverTest.class,
                resolver.resolve("org.maxur.commons.component.application.classresolver.ClassLoaderResolverTest"));
    }

    @Test(expected = ClassNotFoundException.class)
    public void shouldBeReturnExceptionWithUnknownClass() throws Exception {
        resolver.addClassLoader(this.getClass().getClassLoader());
        assertEquals(ClassLoaderResolverTest.class,
                resolver.resolve("test1"));
    }

    @Test
    public void shouldBeReturnEmptyResourcesByUnknownName() throws Exception {
        resolver.addClassLoader(this.getClass().getClassLoader());
        final Iterator<URL> resources = resolver.getResources("test.txt");
        assertFalse(resources.hasNext());
    }

    @Test
    public void shouldBeReturnEmptyResourcesOnClassLoaderNull() throws Exception {
        when(loader.getResources("test.txt")).thenReturn(null);
        resolver.addClassLoader(loader);
        final Iterator<URL> resources = resolver.getResources("test.txt");
        assertFalse(resources.hasNext());
    }

    @Test
    public void shouldBeReturnNotNullResourcesByName() throws Exception {
        final URL url = new URL("http://gmail.com");
        final Enumeration<URL> tmp1 = new FakeEnumeration(url);
        when(loader.getResources("test.txt")).thenReturn(tmp1);
        resolver.addClassLoader(loader);
        final Iterator<URL> resources = resolver.getResources("test.txt");
        assertTrue(resources.hasNext());
        assertEquals(url, resources.next());
    }

    @Test
    public void shouldBeReturnResourcesOnlyOnes() throws Exception {
        final URL url = new URL("http://gmail.com");
        final Enumeration<URL> tmp1 = new FakeEnumeration(url, url);
        when(loader.getResources("test.txt")).thenReturn(tmp1);
        resolver.addClassLoader(loader);
        final Iterator<URL> resources = resolver.getResources("test.txt");
        assertTrue(resources.hasNext());
        resources.next();
        assertFalse(resources.hasNext());
    }


    @Test(expected = WicketRuntimeException.class)
    public void shouldBeReturnExceptionOnErrorInClassLoader() throws Exception {
        when(loader.getResources("test.txt")).thenThrow(new IOException());
        resolver.addClassLoader(loader);
        resolver.getResources("test.txt");
    }


    private static class FakeEnumeration implements Enumeration<URL> {

        private int i;

        private final URL[] urls;

        private FakeEnumeration(URL... urls ) {
            this.urls = urls;
            this.i = urls.length - 1;
        }

        @Override
        public boolean hasMoreElements() {
            return i > -1;
        }

        @Override
        public URL nextElement() {
            return urls[i--];
        }
    }
}
