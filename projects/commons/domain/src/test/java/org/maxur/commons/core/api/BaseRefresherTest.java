package org.maxur.commons.core.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>6/5/12</pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class BaseRefresherTest {

    @Mock
    private Refreshable refreshable;

    @Mock
    private Holder<Refreshable> holder;

    private BaseRefresher<Refreshable> refresher;

    private BaseRefresher<Refreshable> otherRefresher;


    @Before
    public void setUp() throws Exception {
        when(holder.get()).thenReturn(refreshable);
        refresher = new BaseRefresher<>(holder);
        otherRefresher = new BaseRefresher<>(holder);
    }

    @Test
    public void testIsStale() throws Exception {
        assertFalse(refresher.isStale());
        when(holder.get()).thenReturn(null);
        assertTrue(refresher.isStale());
    }

    @Test
    public void testIsStaleAllRefresher() throws Exception {
        when(holder.get()).thenReturn(null);
        assertTrue(refresher.isStale());
        assertTrue(otherRefresher.isStale());
    }


    @Test
    public void testGet() throws Exception {
        assertEquals(refreshable, refresher.get());
    }

    @Test
    public void testGetOnStale() throws Exception {
        when(holder.get()).thenReturn(null);
        assertNull(refresher.get());
        verify(holder).refresh();
    }

    @Test
    public void testUpdate() throws Exception {
        refresher.get();
    }


}
