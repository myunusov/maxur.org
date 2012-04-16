package org.maxur.commons.view.pages.about;

import org.apache.wicket.markup.html.basic.Label;
import org.maxur.commons.view.pages.BasePage;


/**
 * It's Home Page controller class.
 *
 * @author Maxim Yunusov
 * @version 1.0 27.09.11
 */
public class AboutPage extends BasePage {


    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -1644680913059958643L;

    /**
     * It's About Page constructor.
     */
    public AboutPage() {
        final Label version = new Label("version", getApplication().getFrameworkSettings().getVersion());
        version.setRenderBodyOnly(true);
        add(version);
    }
}