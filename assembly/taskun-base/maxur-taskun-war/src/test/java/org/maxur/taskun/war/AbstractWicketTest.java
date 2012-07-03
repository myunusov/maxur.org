package org.maxur.taskun.war;

import com.google.inject.Injector;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.filter.JavaScriptFilteredIntoFooterHeaderResponse;
import org.apache.wicket.markup.html.IHeaderResponseDecorator;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.runner.RunWith;
import org.maxur.commons.component.application.MaxurApplication;

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
        application.setHeaderResponseDecorator(new IHeaderResponseDecorator() {
            public IHeaderResponse decorate(IHeaderResponse response) {
                return new JavaScriptFilteredIntoFooterHeaderResponse(response, MaxurApplication.FOOTER_BUCKET_NAME);
            }
        });
    }
}

