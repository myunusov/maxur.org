package org.maxur.commons.core.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>6/5/12</pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractRefresherTest  {

    @Mock
    private Refreshable spy;

    private AbstractRefresher<Refreshable> refresher;


    @Before
    public void setUp() throws Exception {
        refresher = new RefreshableAbstractRefresher();
    }

    @Test
    public void testIsStale() throws Exception {
        assertFalse(refresher.isStale());
        refresher.update();
        assertTrue(refresher.isStale());
    }

    @Test
    public void testIsStaleAllRefresher() throws Exception {
        refresher.update();
        assertTrue(refresher.isStale());
    }


    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    private class RefreshableAbstractRefresher extends AbstractRefresher<Refreshable> {

        private static final long serialVersionUID = 7635939696733957678L;

        public RefreshableAbstractRefresher() {
            super(AbstractRefresherTest.this.spy);
        }

        @Override
        protected Refreshable refresh() {
            return spy.refresh();
        }

    }
}
