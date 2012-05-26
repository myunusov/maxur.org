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
import static org.junit.Assert.assertEquals;
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
public class AbstractOSGiTrackerHolderTest {

    @Mock
    private ServiceTracker tracker;

    @Mock
    private BundleContext bc;

    private AbstractOSGiTrackerHolder<Object> annotatedTrackerHolder;
    private AbstractOSGiTrackerHolder<Object> trackerHolder;
    private AbstractOSGiTrackerHolder<Object> invalidTrackerHolder;


    @Before
    public void setUp() throws Exception {
        annotatedTrackerHolder = new AbstractOSGiTrackerHolder<Object>(Object.class, named("FakeObject")) {
            @Override
            protected ServiceTracker makeTracker(final BundleContext bc) throws InvalidSyntaxException {
                return tracker;
            }
        };
        trackerHolder = new AbstractOSGiTrackerHolder<Object>(Object.class) {
            @Override
            protected ServiceTracker makeTracker(final BundleContext bc) throws InvalidSyntaxException {
                return tracker;
            }
        };
        invalidTrackerHolder = new AbstractOSGiTrackerHolder<Object>(Object.class) {
            @Override
            protected ServiceTracker makeTracker(final BundleContext bc) throws InvalidSyntaxException {
                throw new InvalidSyntaxException("Error", "in invalid class");
            }
        };

    }

    @Test
    public void shouldBeReturnProvidedClass() throws Exception {
        assertSame(Object.class, trackerHolder.getProvidedClass());
    }

    @Test
    public void shouldBeAnnotated() throws Exception {
        assertTrue(annotatedTrackerHolder.isAnnotated());
    }

    @Test
    public void shouldBeNotAnnotated() throws Exception {
        assertFalse(trackerHolder.isAnnotated());
    }


    @Test
    public void shouldBeReturnAnnotation() throws Exception {
        assertEquals(named("FakeObject"), annotatedTrackerHolder.getAnnotation());
    }

    @Test
    public void shouldBeStarted() throws Exception {
        trackerHolder.start(bc, "id");
        verify(tracker).open();
    }

    @Test(expected = AssertionError.class)
    public void shouldBeNotStarted() throws Exception {
        invalidTrackerHolder.start(bc, "id");
        verifyNoMoreInteractions(tracker);
    }

    @Test
    public void shouldBeStoppedWithoutStart() throws Exception {
        trackerHolder.stop();
    }

    @Test
    public void shouldBeStopped() throws Exception {
        trackerHolder.start(bc, "id");
        trackerHolder.stop();
        verify(tracker).close();
    }

    @Test
    public void shouldBeReset() throws Exception {
        trackerHolder.start(bc, "id");
        trackerHolder.reset(bc);
        verify(tracker).close();
        verify(tracker, times(2)).open();
    }


}
