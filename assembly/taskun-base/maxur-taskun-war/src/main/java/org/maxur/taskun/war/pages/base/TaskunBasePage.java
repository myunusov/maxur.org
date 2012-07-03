package org.maxur.taskun.war.pages.base;


import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;
import org.maxur.adapter.bootstrap.BootstrapCoreBehavior;
import org.maxur.adapter.jquery.JQueryBehavior;
import org.maxur.commons.component.application.MaxurApplication;
import org.maxur.commons.component.behavior.NullBehavior;
import org.maxur.commons.component.behavior.ThemeBehavior;
import org.maxur.commons.component.page.BasePage;
import org.maxur.taskun.war.panels.mainmenu.MenuPanel;

/**
 * @author Maxim Yunusov
 * @version 1.0 01.06.12
 */
public abstract class TaskunBasePage extends BasePage {

    private static final long serialVersionUID = 2972619145856646795L;

    @Inject
    @Named("blue_theme")
    private ThemeBehavior themeBehavior;


    /**
     * It's Base Page constructor.
     */
    public TaskunBasePage() {
        add(new HeaderResponseContainer(MaxurApplication.FOOTER_BUCKET_NAME, MaxurApplication.FOOTER_BUCKET_NAME));

        add(new Label("application.title", new ResourceModel("application.title")));
//        add(new HeaderPanel("header"));
        add(new MenuPanel("menu"));
        add(new FooterPanel("footer"));

        add(getThemeBehavior());
        add(new JQueryBehavior());
        add(new BootstrapCoreBehavior());
    }

    private Behavior getThemeBehavior() {
        return themeBehavior != null ? themeBehavior.asBehavior(): NullBehavior.get();
    }


}
