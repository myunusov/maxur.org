package org.maxur.commons.view.pages.home;

import org.apache.wicket.markup.html.basic.Label;
import org.maxur.commons.view.pages.BasePage;


/**
 * It's Home Page controller class.
 *
 * @author Maxim Yunusov
 * @version 1.0 27.09.11
 */
public class HomePage extends BasePage {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -306372547626459776L;


    /**
     * It's Base Page constructor.
     */
    public HomePage() {
        add(new Label("version", getApplication().getFrameworkSettings().getVersion()));
    }
}