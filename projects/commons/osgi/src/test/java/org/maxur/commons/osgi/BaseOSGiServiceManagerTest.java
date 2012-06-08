package org.maxur.commons.osgi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.util.tracker.ServiceTracker;

import static com.google.inject.name.Names.named;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.05.12
 */
@RunWith(MockitoJUnitRunner.class)
public class BaseOSGiServiceManagerTest {

    @Mock
    private ServiceTracker tracker;

    @Mock
    private BundleContext bc;

    private BaseOSGiServiceManager<Object> serviceManager;
    private BaseOSGiServiceManager<Object> annotatedServiceManager;
    private BaseOSGiServiceManager<Object> invalidServiceManager;


    @Before
    public void setUp() throws Exception {
        serviceManager = new BaseOSGiServiceManager<Object>(Object.class, false, null) {
            @Override
            protected ServiceTracker makeTracker(final BundleContext bc) throws InvalidSyntaxException {
                return tracker;
            }
        };
        annotatedServiceManager = new BaseOSGiServiceManager<Object>(Object.class, false, named("FakeObject")) {
            @Override
            protected ServiceTracker makeTracker(final BundleContext bc) throws InvalidSyntaxException {
                return tracker;
            }
        };
        invalidServiceManager = new BaseOSGiServiceManager<Object>(Object.class, false, null) {
            @Override
            protected ServiceTracker makeTracker(final BundleContext bc) throws InvalidSyntaxException {
                throw new InvalidSyntaxException("Error", "in invalid class");
            }
        };

    }

    @Test
    public void shouldBeReturnProvidedClass() throws Exception {
        assertSame(Object.class, serviceManager.getServiceDescriptions().getProvidedClass());
    }

    @Test
    public void shouldBeAnnotated() throws Exception {
        assertTrue(annotatedServiceManager.getServiceDescriptions().isAnnotated());
    }

    @Test
    public void shouldBeNotAnnotated() throws Exception {
        assertFalse(serviceManager.getServiceDescriptions().isAnnotated());
    }


    @Test
    public void shouldBeReturnAnnotation() throws Exception {
        assertEquals(named("FakeObject"), annotatedServiceManager.getServiceDescriptions().getAnnotation());
    }

    @Test
    public void shouldBeStarted() throws Exception {
        serviceManager.start(bc, "id");
        verify(tracker).open();
    }

    @Test(expected = AssertionError.class)
    public void shouldBeNotStarted() throws Exception {
        invalidServiceManager.start(bc, "id");
        verifyNoMoreInteractions(tracker);
    }

    @Test
    public void shouldBeStoppedWithoutStart() throws Exception {
        serviceManager.stop();
    }

    @Test
    public void shouldBeStopped() throws Exception {
        serviceManager.start(bc, "id");
        serviceManager.stop();
        verify(tracker).close();
    }

    @Test
    public void shouldBeReset() throws Exception {
        serviceManager.start(bc, "id");
        serviceManager.reset(bc);
        verify(tracker).close();
        verify(tracker, times(2)).open();
    }


}
