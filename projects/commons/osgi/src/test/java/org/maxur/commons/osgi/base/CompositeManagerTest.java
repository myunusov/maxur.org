package org.maxur.commons.osgi.base;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.BundleContext;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;

/**
 * @author Maxim Yunusov
 * @version 1.0 20.06.12
 */
@RunWith(MockitoJUnitRunner.class)
public class CompositeManagerTest {

    @Mock
    private OSGiManager manager;

    @Mock
    private OSGiManager manager1;

    @Mock
    private BundleContext bc;

    private CompositeManager<OSGiManager> compositeManager;


    @Before
    public void setUp() throws Exception {
        compositeManager = new CompositeManager<>();
    }

    @Test
    public void shouldBeAddNewManager() throws Exception {
        compositeManager.add(manager);
        assertEquals(1, compositeManager.get().size());
        assertEquals(manager, compositeManager.get().iterator().next());
    }

    @Test
    public void shouldBeAddNewManagers() throws Exception {
        compositeManager.add(manager);
        compositeManager.add(manager1);
        assertEquals(2, compositeManager.get().size());
        assertTrue(compositeManager.get().contains(manager));
        assertTrue(compositeManager.get().contains(manager1));
    }


    @Test
    public void shouldBeStartAddedManager() throws Exception {
        compositeManager.add(manager);
        compositeManager.start(bc, "pid");
        verify(manager).start(bc, "pid");
    }

    @Test
    public void shouldBeStopAddedManager() throws Exception {
        compositeManager.add(manager);
        compositeManager.stop();
        verify(manager).stop();
    }

    @Test
    public void shouldBeAddSameManagersAtOne() throws Exception {
        compositeManager.add(manager);
        compositeManager.add(manager);
        assertEquals(1, compositeManager.get().size());
    }

}
