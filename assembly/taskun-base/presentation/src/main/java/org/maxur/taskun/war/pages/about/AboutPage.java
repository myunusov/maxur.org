package org.maxur.taskun.war.pages.about;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.wicket.markup.html.basic.Label;
import org.maxur.taskun.war.pages.BasePage;


/**
 * It's Home Page controller class.
 *
 * @author Maxim Yunusov
 * @version 1.0 27.09.11
 */
public class AboutPage extends BasePage {

    @Inject
    @Named("application.title")
    private String title;

    @Inject
    @Named("application.version")
    private String version;

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -1644680913059958643L;

    /**
     * It's About Page constructor.
     */
    public AboutPage() {
        final Label applicationName = new Label("application", String.format("%s (Version : %s)", title, version));
        applicationName.setRenderBodyOnly(true);
        add(applicationName);
        final Label version = new Label("version", getApplication().getFrameworkSettings().getVersion());
        version.setRenderBodyOnly(true);
        add(version);
    }
}
