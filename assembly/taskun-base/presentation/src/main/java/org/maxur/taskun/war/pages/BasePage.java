package org.maxur.taskun.war.pages;

import com.google.inject.Inject;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;
import org.maxur.commons.component.behavior.NullBehavior;
import org.maxur.commons.component.behavior.ThemeBehavior;
import org.maxur.commons.component.mainmenu.MenuPanel;

/**
 * It's Base Page controller class.
 *
 * @author Maxim Yunusov
 * @version 1.0 27.09.11
 */
public class BasePage extends WebPage {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -4715929870257591604L;

    @Inject
    private ThemeBehavior themeBehavior;

    /**
     * It's Base Page constructor.
     */
    public BasePage() {
        add(getThemeBehavior());
        add(new Label("application.title", new ResourceModel("application.title").wrapOnAssignment(this)));
        add(new HeaderPanel("header"));
        add(new MenuPanel("menu"));
        add(new FooterPanel("footer"));
    }

    private Behavior getThemeBehavior() {
        return themeBehavior != null ? themeBehavior.asBehavior(): NullBehavior.get();
    }

}
