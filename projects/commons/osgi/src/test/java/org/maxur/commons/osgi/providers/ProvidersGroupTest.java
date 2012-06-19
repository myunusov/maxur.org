package org.maxur.commons.osgi.providers;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.commons.osgi.holder.InjectorHolderList;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import static com.google.inject.name.Names.named;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.05.12
 */
@RunWith(MockitoJUnitRunner.class)
public class ProvidersGroupTest {

    @Mock
    private ServiceTracker tracker;

    @Mock
    private BundleContext bc;

    private ProvidersGroup serviceManager;
    private ProvidersGroup annotatedServiceManager;
    private ProvidersGroup invalidServiceManager;


    @Before
    public void setUp() throws Exception {
        serviceManager = new ProvidersGroup(Object.class, false, null);
        annotatedServiceManager = new ProvidersGroup(Object.class, false, named("FakeObject"));
        invalidServiceManager = new ProvidersGroup(Object.class, false, null);
    }

    @Test
    public void shouldBeReturnProvidedClass() throws Exception {
        assertSame(Object.class, serviceManager.getProvidedClass());
    }

    @Test
    public void shouldBeAnnotated() throws Exception {
        assertTrue(annotatedServiceManager.isAnnotated());
    }

    @Test
    public void shouldBeNotAnnotated() throws Exception {
        assertFalse(serviceManager.isAnnotated());
    }


    @Test
    public void shouldBeReturnAnnotation() throws Exception {
        assertEquals(named("FakeObject"), annotatedServiceManager.getAnnotation());
    }

    @Ignore
    @Test
    public void shouldBeStarted() throws Exception {
        InjectorHolderList.start("id");
        serviceManager.start(bc, "id");
        verify(tracker).open();
    }

    @Ignore
    @Test(expected = AssertionError.class)
    public void shouldBeNotStarted() throws Exception {
        InjectorHolderList.start("id");
        invalidServiceManager.start(bc, "id");
        verifyNoMoreInteractions(tracker);
    }

    @Ignore
    @Test
    public void shouldBeStopped() throws Exception {
        InjectorHolderList.start("id");
        serviceManager.start(bc, "id");
        serviceManager.stop();
        verify(tracker).close();
    }


    @Test
    public void shouldBeStoppedWithoutStart() throws Exception {
        serviceManager.stop();
    }


}
