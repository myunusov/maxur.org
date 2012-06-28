package org.maxur.adapter.yaml4;

import com.google.inject.Injector;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.runner.RunWith;

/**
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 *
 * see http://blog.yanivkessler.com/2010/06/writing-unit-tests-for-guicey-wicket.html
 */
@RunWith(MaxurGuiceTestRunner.class)
public abstract class AbstractWicketTest {

    private final WicketTester tester;

    protected final WicketTester getTester() {
        return tester;
    }

    public AbstractWicketTest(Injector injector) {
        tester = new WicketTester();
        final WebApplication application = tester.getApplication();
        application.getComponentInstantiationListeners().add(new GuiceComponentInjector(application, injector));
    }
}

