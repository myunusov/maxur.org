package org.maxur.commons.contract;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.matcher.Matchers;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * @author Maxim Yunusov
 * @version 1.0 17.06.12
 */
@RunWith(MockitoJUnitRunner.class)
public class InterceptorTest {

    @Inject
    private InterceptedService service;

    @Mock
    private MethodInterceptor interceptor;

    @Before
    public void setUp() throws Exception {
        Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(InterceptedService.class).to(FakeInterceptedService.class);
                bindInterceptor(
                        Matchers.any(),
                        Matchers.annotatedWith(org.maxur.commons.contract.annotation.Inv.class),
                        interceptor);
            }
        }).injectMembers(this);
    }

    @Test
    public void shouldBeInjectService() throws Exception {
        Assert.assertNotNull(service);
    }

    @Test
    public void shouldBeIntercepted() throws Throwable {
        service.process();
        ArgumentCaptor<MethodInvocation> argument = ArgumentCaptor.forClass(MethodInvocation.class);
        verify(interceptor).invoke(argument.capture());
        assertEquals("process", argument.getValue().getMethod().getName());
    }


    public static class Interceptor implements MethodInterceptor {
        public Object invoke(MethodInvocation invocation) throws Throwable {
            throw new IllegalStateException(invocation.getMethod().getName() + " not allowed on weekends!");
//            return invocation.proceed();
        }
    }

}
