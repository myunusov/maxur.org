package org.maxur.commons.osgi.holder;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.commons.osgi.base.MutableModule;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * @author Maxim Yunusov
 * @version 1.0 19.06.12
 */
@RunWith(MockitoJUnitRunner.class)
public class InjectorHolderTest {

    private static final int EMPTY_INJECTOR_BINDINGS_COUNT = 3;

    private InjectorHolder holder;

    @Mock
    private MutableModule module;

    @Before
    public void setUp() throws Exception {
        holder = new InjectorHolder();
    }

    @Test
    public void shouldBeReturnEmptyInjectorOnInit() throws Exception {
        final Injector injector = holder.get();
        assertNull(injector.getParent());
        assertEquals(EMPTY_INJECTOR_BINDINGS_COUNT, injector.getBindings().size());
    }

    @Test
    public void shouldBeReturnSameInjectorWithoutUpdate() throws Exception {
        assertSame(holder.get(), holder.get());
    }

    @Test
    public void shouldBeReturnNewInjectorOnUpdate() throws Exception {
        final Injector injector = holder.get();
        holder.update();
        assertNotSame(injector, holder.get());
    }

    @Test
    public void shouldBeReturnNewInjectorWithParent() throws Exception {
        final Injector parentInjector = Guice.createInjector();
        holder.setParentInjector(parentInjector);
        assertSame(parentInjector, holder.get().getParent());
    }

    @Test
    public void shouldBeObserveNewModule() throws Exception {
        holder.addModule(module);
        Mockito.verify(module).addObserver(holder);
    }

    @Test
    public void shouldBeReturnNewInjectorWithModule() throws Exception {
        holder.addModule(new FakeModule());
        assertEquals(EMPTY_INJECTOR_BINDINGS_COUNT + 1, holder.get().getBindings().size());
    }

    @Test
    public void shouldBeReturnNewInjectorOnModuleUpdate() throws Exception {
        final FakeModule fakeModule = new FakeModule();
        holder.addModule(fakeModule);
        final Injector injector = holder.get();
        fakeModule.update();
        assertNotSame(injector, holder.get());
    }

    private static class FakeModule extends MutableModule {
        @Override
        protected void configure() {
            bind(FakeModule.class);
        }
    }
}
