package org.maxur.commons.view.config;

import org.apache.wicket.markup.html.WebPage;
import org.maxur.commons.view.api.PageProvider;
import org.maxur.commons.view.pages.home.HomePage;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>5/10/12</pre>
 */
public class HomePageProvider implements PageProvider {

    @Override
    public Class<? extends WebPage> get() {
        return HomePage.class;
    }

}
