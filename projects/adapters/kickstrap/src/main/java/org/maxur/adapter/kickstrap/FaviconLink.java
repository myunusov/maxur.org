package org.maxur.adapter.kickstrap;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.model.Model;

/**
 * @author Maxim Yunusov
 * @version 1.0 30.05.12
 */
public class FaviconLink extends ExternalLink {

    private static final long serialVersionUID = 3372718976863453200L;

    public FaviconLink(final String id, final Model<String> href) {
        super(id, href);
        add(new AttributeModifier("type", "image/x-icon"));
        add(new AttributeModifier("rel", "shortcut icon"));
    }

}
