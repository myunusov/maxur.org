package org.maxur.commons.view;

import com.google.inject.Injector;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.maxur.commons.view.pages.home.HomePage;

/**
 * @author Maxim Yunusov
 * @version 1.0 27.09.11
 */
public class TaskunApplication extends WebApplication {

    private static final String CURRENT_ENCODING = "UTF-8";
    private final Injector injector;

    public TaskunApplication(final Injector injector) {
        this.injector = injector;
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
        getMarkupSettings().setDefaultMarkupEncoding(CURRENT_ENCODING);
        getRequestCycleSettings().setResponseRequestEncoding(CURRENT_ENCODING);
        getComponentInstantiationListeners().add(new GuiceComponentInjector(this, injector));
    }

}