package org.maxur.commons.component.mainmenu;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.link.ILinkListener;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import org.maxur.commons.view.api.Command;

/**
 * Menu item Links Panel component.
 *
 * @author Maxim Yunusov
 * @version 1.0 15.04.12
 */
public class MenuItemPanel extends WebComponent implements ILinkListener {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -899628430579241468L;

    private final Model<Command> model;

    /**
     * Constructs the MenuItemPanel instance.
     *
     * @param id   The link identifier
     * @param model The Menu Item descriptor.
     */
    public MenuItemPanel(final String id, final Model<Command> model) {
        super(id, model);
        this.model = model;
        final Command command = model.getObject();
        command.bind(this);
        setRenderBodyOnly(true);
    }

    /** {@inheritDoc} */
    @Override
    public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
        final Command command = model.getObject();
        final StringBuilder markup = new StringBuilder();
        if (!command.isActive()) {
            renderMenuItem(markup);
        } else {
            renderActiveMenuItem(markup);
        }
        replaceComponentTagBody(markupStream, openTag, markup);
    }

    private void renderMenuItem(final StringBuilder markup) {
        final CharSequence url = getURL();
        markup.append("<a href=\"").append(url).append("\">");
        markup.append(getItemTitle().getObject());
        markup.append("</a>");
    }

    private void renderActiveMenuItem(final StringBuilder markup) {
        markup.append("<strong>");
        markup.append(getItemTitle().getObject());
        markup.append("</strong>");
    }

    private IModel<String> getItemTitle() {
        final Command command = model.getObject();
        return new ResourceModel(command.getTitleKey()).wrapOnAssignment(getPage());
    }

    /** {@inheritDoc} */
    @Override
    public void onLinkClicked() {
        final Command command = model.getObject();
        command.execute();
    }

    /**
     * <p>getURL</p>
     *
     * @return a {@link java.lang.CharSequence} object.
     */
    protected CharSequence getURL() {
        return urlFor(ILinkListener.INTERFACE, new PageParameters());
    }

}
