package org.maxur.commons.osgi.base;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.commons.core.api.Observer;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author Maxim Yunusov
 * @version 1.0 19.06.12
 */
@RunWith(MockitoJUnitRunner.class)
public class MutableModuleTest {

    @Mock
    private Observer observer;

    private MutableModule module;

    @Before
    public void setUp() throws Exception {
        module = new MutableModule() {
            @Override
            protected void configure() {
            }
        };
    }

    @Test
    public void shouldBeAddNewObserver() throws Exception {
        module.addObserver(observer);
        module.update();
        verify(observer).update();
    }

    @Test
    public void shouldBeRemovedObserver() throws Exception {
        module.addObserver(observer);
        module.removeObserver(observer);
        module.update();
        verifyNoMoreInteractions(observer);
    }
}