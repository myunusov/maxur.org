package org.maxur.commons.view.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.maxur.commons.view.components.menu.MenuPanel;

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

    /**
     * It's Base Page constructor.
     */
    public BasePage() {
        add(new HeaderPanel("header"));
        add(new FooterPanel("footer"));
        add(new MenuPanel("menu"));
        IModel<String> model = new ResourceModel("application.title").wrapOnAssignment(this);
        add(new Label("application.title", model));
    }
}
