package org.maxur.theme.gray;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.resource.CssResourceReference;
import org.maxur.commons.component.behavior.BaseThemeBehavior;
import org.maxur.commons.view.api.StyleBehavior;

/**
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class GrayThemeBehavior extends BaseThemeBehavior {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = 5450247904080075496L;

    @Inject  @Named("yaml_core")
    private StyleBehavior core;

    @Inject  @Named("yaml_screen")
    private StyleBehavior screen;

    @Inject  @Named("yaml_navigation")
    private StyleBehavior navigation;

    @Inject  @Named("yaml_printer")
    private StyleBehavior printer;

    @Inject  @Named("yaml_forms")
    private StyleBehavior forms;


    @Override
    public void renderHead(final Component component, final IHeaderResponse response) {
        super.renderHead(component, response);
        response.render(CssHeaderItem.forReference(
                new CssResourceReference(this.getClass(), "/css/layout.css")
        ));
    }

    @Override
    public Behavior asBehavior() {
        return new BaseThemeBehavior(
                core.asBehavior(),
                screen.asBehavior(),
                navigation.asBehavior(),
                printer.asBehavior(),
                forms.asBehavior(),
                this
        );
    }

}
