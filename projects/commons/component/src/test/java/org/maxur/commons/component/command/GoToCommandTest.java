package org.maxur.commons.component.command;

import org.apache.wicket.ThreadContext;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.cycle.RequestCycle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Maxim Yunusov
 * @version 1.0 08.05.12
 */
@RunWith(MockitoJUnitRunner.class)
public class GoToCommandTest {

    @Mock
    private IRequestableComponent component;

    @Mock
    private IRequestablePage page;

    @Mock
    private RequestCycle requestCycle;

    private GoToCommand.Builder builder;

    @Before
    public void setUp() throws Exception {
        builder = GoToCommand.builder();
    }

    @Test
    public void shouldBeNotNullBuilder() throws Exception {
        assertTrue("Builder should be Not Null", builder != null);
    }

    @Test (expected=AssertionError.class)
    public void shouldBeNotBuildWithoutTitleKey() throws Exception {
        builder.setAsTarget(FakePage.class)
               .build();
    }

    @Test (expected=AssertionError.class)
    public void shouldBeNotBuildWithoutTargetPage() throws Exception {
        builder.withTitleKey("fake.key")
               .build();
    }


    @Test
    public void shouldBeBuildWithTargetPageAndTitleKey() throws Exception {
        final GoToCommand command = builder.withTitleKey("fake.key")
                .setAsTarget(FakePage.class)
                .build();
        assertTrue("Command should be Not Null", command != null);
        assertEquals("Command should be GoToCommand type", command.getClass(), GoToCommand.class);
    }

    @Test
    public void shouldBeIsNotActiveWithoutComponent() throws Exception {
        final GoToCommand command = builder.withTitleKey("fake.key")
                .setAsTarget(FakePage.class)
                .build();
        assertFalse(command.isActive());
    }

    @Test
    public void shouldBeReturnComponentWhichWasBind() throws Exception {
        final GoToCommand command = builder.withTitleKey("fake.key")
                .setAsTarget(FakePage.class)
                .build();
        command.bind(component);
        assertSame(component, command.getComponent());
    }

    @Test
    public void shouldBeNotActiveWithComponentFromOtherPage() throws Exception {
        when(component.getPage()).thenReturn(page);
        final GoToCommand command = builder.withTitleKey("fake.key")
                .setAsTarget(FakePage.class)
                .build();
        command.bind(component);
        assertFalse(command.isActive());
    }

    @Test
    public void shouldBeActiveWithComponentFromSamePage() throws Exception {
        when(component.getPage()).thenReturn(page);
        final GoToCommand command = builder.withTitleKey("fake.key")
                .setAsTarget(page.getClass())
                .build();
        command.bind(component);
        assertTrue(command.isActive());
    }

    @Test
    public void shouldBeReturnTitleKey() throws Exception {
        final GoToCommand command = builder.withTitleKey("fake.key")
                .setAsTarget(FakePage.class)
                .build();
        assertSame("fake.key", command.getTitleKey());
    }

    @Test
    public void shouldBeWentToTargetClass() throws Exception {
        ThreadContext.setRequestCycle(requestCycle);
        final GoToCommand command = builder.withTitleKey("fake.key")
                .setAsTarget(FakePage.class)
                .build();
        command.execute();
        verify(requestCycle).setResponsePage(FakePage.class);
    }


    private static class FakePage extends WebPage {
        private static final long serialVersionUID = -5723596391089882784L;
    }

}
