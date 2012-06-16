package org.maxur.commons.core.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Maxim Yunusov
 * @version 1.0 06.06.12
 */
@RunWith(MockitoJUnitRunner.class)
public class OuterIteratorTest {

    @Mock
    private Iterable<Object> iterable;

    @Mock
    private Iterator<Object> innerIterator;

    private OuterIterator<Object> iterator;



    @Before
    public void setUp() throws Exception {
        when(iterable.iterator()).thenReturn(innerIterator);
        iterator = OuterIterator.get(iterable);
    }

    @Test
    public void shouldBeReturnNullBeforeNextWillBeCalled() throws Exception {
        assertNull(iterator.element());
    }

    @Test
    public void shouldBeCallNextInInnerIterator() throws Exception {
        iterator.next();
        verify(innerIterator).next();
    }


    @Test
    public void shouldBeReturnCurrentElementOnElement() throws Exception {
        final Object fake = new Object();
        when(innerIterator.next()).thenReturn(fake);
        iterator.next();
        assertEquals(fake, iterator.element());
        assertEquals(fake, iterator.element());
    }

    @Test
    public void shouldBeReturnHasNextFromInnerIterator() throws Exception {
        when(innerIterator.hasNext()).thenReturn(true);
        assertEquals(innerIterator.hasNext(), iterator.hasNext());
    }

}
