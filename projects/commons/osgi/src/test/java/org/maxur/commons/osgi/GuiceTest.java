package org.maxur.commons.osgi;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static com.google.inject.name.Names.named;
import static org.junit.Assert.assertEquals;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>6/8/12</pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class GuiceTest {

    private static final String KEY = "a";


    private Map<String,String> constants;

    private FakeModule module;

    private Injector injector;


    @Inject @Named(KEY)
    private String constant;


    @Before
    public void setUp() throws Exception {
        constants = new HashMap<>();
        constants.put(KEY, "a1");
        module = new FakeModule(constants);
        injector = Guice.createInjector(module);
    }

    @Test
    public void shouldBeInjected() throws Exception {
        injector.injectMembers(this);
        assertEquals("a1", constant);
    }

    @Test
    public void shouldBeInjectedByOldValueOnChangeModule() throws Exception {
        constants.put(KEY, "a2");
        injector.injectMembers(this);
        assertEquals("a1", constant);
    }

    @Test
    public void shouldBeInjectedByNewValueOnUpdateInjector() throws Exception {
        constants.put(KEY, "a2");
        injector = Guice.createInjector(module);
        injector.injectMembers(this);
        assertEquals("a2", constant);
    }


    private static class FakeModule extends AbstractModule {

        private final Map<String, String> constants;

        public FakeModule(final Map<String, String> constants) {
            this.constants = constants;
        }

        @Override
        protected void configure() {
            for (String constant : constants.keySet()) {
                bindConstant().annotatedWith(named(constant)).to(constants.get(constant));
            }
        }
    }
}
