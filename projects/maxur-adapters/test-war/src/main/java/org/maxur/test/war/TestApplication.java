package org.maxur.test.war;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.protocol.http.WebApplication;
import org.maxur.adapter.kickstrap.HTMLTagModifier;
import org.maxur.adapter.kickstrap.HTMLTagModifierImpl;
import org.maxur.commons.component.behavior.BaseThemeBehavior;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.view.api.StyleBehavior;

import static com.google.inject.name.Names.named;

/**
 * @author Maxim Yunusov
 * @version 1.0 29.05.12
 */
public class TestApplication extends WebApplication {

    public TestApplication() {
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    /**
     * It's template method. It can not be overrides.
     * One can use 'doInit' for override purpose.
     *
     * {@inheritDoc}
     *
     */
    @Override
    protected final void init() {
        getComponentInstantiationListeners().add(createInjector());
        IPackageResourceGuard guard = Application.get()
                .getResourceSettings()
                .getPackageResourceGuard();
        final SecurePackageResourceGuard securePackageResourceGuard = (SecurePackageResourceGuard) guard;
        securePackageResourceGuard.addPattern("+*.woff");
        securePackageResourceGuard.addPattern("+*.ttf");
    }

    private GuiceComponentInjector createInjector() {
        final Injector injector = Guice.createInjector(new TestModule());
        injector.injectMembers(this);
        return new GuiceComponentInjector(this, injector);
    }

    private static class TestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(HTMLTagModifier.class).to(HTMLTagModifierImpl.class);
            bind(WebBrowserDetector.class).to(WicketWebBrowserDetector.class);
            bind(StyleBehavior.class).annotatedWith(named("theme")).to(BaseThemeBehavior.class);
        }
    }
}


