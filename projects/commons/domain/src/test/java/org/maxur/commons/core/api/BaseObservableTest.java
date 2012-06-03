package org.maxur.commons.core.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.commons.core.api.BaseObservable;
import org.maxur.commons.core.api.Observer;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author Maxim Yunusov
 * @version 1.0 26.05.12
 */
@RunWith(MockitoJUnitRunner.class)
public class BaseObservableTest {

    @Mock
    private Observer observer;

    private BaseObservable observable;

    @Before
    public void setUp() throws Exception {
        observable = new BaseObservable();
    }

    @Test
    public void shouldBeAddNewObserver() throws Exception {
        observable.addObserver(observer);
        observable.notifyObservers();
        verify(observer).update();
    }

    @Test
    public void shouldBeRemovedObserver() throws Exception {
        observable.addObserver(observer);
        observable.removeObserver(observer);
        observable.notifyObservers();
        verifyNoMoreInteractions(observer);
    }

}
