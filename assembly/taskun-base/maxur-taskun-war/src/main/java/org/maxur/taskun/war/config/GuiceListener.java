package org.maxur.taskun.war.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.maxur.commons.osgi.holder.InjectorHolder;
import org.maxur.commons.osgi.holder.InjectorHolderList;


/**
 * The GuiceListener class loads our modules.
 *
 * see http://blog.yanivkessler.com/2010/05/wicket-and-guice-alternate-route.html
 */
public class GuiceListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        final InjectorHolder holder = InjectorHolderList.holder(BaseTaskunActivator.PID);
        holder.setParentInjector(createInjector());
        return holder.get();
    }

    private Injector createInjector() {
        return Guice.createInjector(new WebModule(), new ApplicationModule());
    }


}