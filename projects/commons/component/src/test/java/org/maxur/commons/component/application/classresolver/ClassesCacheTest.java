package org.maxur.commons.component.application.classresolver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author Maxim Yunusov
 * @version 1.0 19.05.12
 */
@RunWith(MockitoJUnitRunner.class)
public class ClassesCacheTest {

    @Mock
    private ClassResolver resolver;

    private ClassesCache cache;

    @Before
    public void setUp() throws Exception {
        cache = new ClassesCache();
    }

    @Test(expected = ClassNotFoundException.class)
    public void shouldBeReturnExceptionOnCallWithoutNextResolver() throws Exception {
        cache.resolve("test1");
    }

    @Test
    public void shouldBeReturnResultFromNextResolver() throws Exception {
        cache.wrap(resolver);
        cache.resolve("test1");
        verify(resolver).resolve("test1");
    }

    @Test
    public void shouldBeReturnResultFromCacheOnDoubleCall() throws Exception {
        cache.wrap(resolver);
        cache.resolve("test1");
        verify(resolver).resolve("test1");
        cache.resolve("test1");
        verifyNoMoreInteractions(resolver);
    }


}
