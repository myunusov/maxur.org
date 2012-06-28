package org.maxur.adapter.kickstrap;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;

/**
* @author Maxim Yunusov
* @version 1.0 30.05.12
*/
public class ApplicationDescription extends WebMarkupContainer {

    private static final long serialVersionUID = -1862755015470976169L;

    public ApplicationDescription(final String id, final Model<String> model) {
        super(id);
        add(new AttributeModifier("content", model));
    }
}
