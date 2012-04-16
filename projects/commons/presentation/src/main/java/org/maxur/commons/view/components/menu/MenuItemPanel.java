package org.maxur.commons.view.components.menu;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.maxur.commons.view.components.model.Command;

/**
 * Menu item Links Panel component.
 *
 * @author Maxim Yunusov
 * @version 1.0 15.04.12
 */
public class MenuItemPanel extends Panel {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -899628430579241468L;

    /**
     * Constructs the MenuItemPanel instance.
     *
     * @param id   The link identifier
     * @param model The Menu Item descriptor.
     */
    public MenuItemPanel(final String id, final Model<Command> model) {
        super(id, model);
        model.getObject().setComponent(this);
        setRenderBodyOnly(true);
        add(new RegularMenuLink("menu_link", model));
        add(new AjaxMenuLink("ajax_menu_link", model));
    }

    private static class RegularMenuLink extends Link<Command> {

        private static final long serialVersionUID = 1499964362961000751L;

        public RegularMenuLink(final String id, Model<Command> model) {
            super(id, model);
            final Command command = model.getObject();
            setVisible(!command.isAjax());
            final Label label = new Label("menu_item_title", new ResourceModel(command.getTitleKey()));
            label.setRenderBodyOnly(true);
            add(label);
        }

        /**
         * Is called on Event.
         */
        @Override
        public void onEvent(IEvent<?> event) {
            super.onEvent(event);
            if (getModelObject() instanceof GoToCommand && event.getPayload() instanceof PageUpdate) {
                final PageUpdate update = (PageUpdate) event.getPayload();
                final GoToCommand command = (GoToCommand) getModelObject();
                final IModel<String> className = command.isActive() ?
                        new Model<>("active") : new Model<>("");
                add(new AttributeModifier("class", className));
            }
        }

        /**
         * Is called on Click.
         */
        @Override
        public void onClick() {
            final Command item = getModelObject();
            item.execute();
        }
    }

    private class AjaxMenuLink extends AjaxLink<Command> {

        private static final long serialVersionUID = -7812753270345223776L;

        public AjaxMenuLink(String id, Model<Command> model) {
            super(id, model);
            final Command command = model.getObject();
            setVisible(command.isAjax());
            add(new Label("menu_item_title", new ResourceModel(command.getTitleKey())));

        }

        /**
         * Is called on Click.
         */
        @Override
        public void onClick(final AjaxRequestTarget target) {
            final Command item = getModelObject();
            item.execute(target);
        }

    }
}
