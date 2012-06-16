package org.maxur.commons.core.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Maxim Yunusov
 * @version 1.0 14.06.12
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractRefresherTest {

    @Mock
    private Refreshable refreshable;

    private Refresher<Refreshable> refresher;

    @Before
    public void setUp() throws Exception {
        refresher = new FakeRefresher(refreshable);
    }

    @Test
    public void testIsStale() throws Exception {
        assertFalse(refresher.isStale());
    }

    @Test
    public void testGet() throws Exception {
        assertEquals(refreshable, refresher.get());
    }

    @Test
    public void testUpdate() throws Exception {
        refresher.get();
    }

    @Test
    public void testSerialization() throws Exception {
        //Serialization
        Refresher<Refreshable> object1 = new FakeRefresher(new FakeRefreshable());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(object1);

        //De-serialization
        ByteArrayInputStream bis = new   ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);
        Refresher<Refreshable> object2 = (Refresher<Refreshable>) in.readObject();


        assertEquals(true ,object2.isStale());
        assertEquals(object1.get() ,object2.get());

    }

    private static class FakeRefresher extends AbstractRefresher<Refreshable> {

        private static final long serialVersionUID = 5164186347521732565L;

        private final Refreshable refreshable;

        public FakeRefresher(Refreshable refreshable) {
            this.refreshable = refreshable;
        }

        @Override
        public Refreshable get() {
            return refreshable;
        }
    }

    private static class FakeRefreshable implements Refreshable, Serializable {
        private static final long serialVersionUID = -5193709987939126619L;

        @Override
        public void refresh() {
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof FakeRefreshable || super.equals(obj);
        }
    }
}
