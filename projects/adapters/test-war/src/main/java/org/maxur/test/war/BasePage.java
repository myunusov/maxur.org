package org.maxur.test.war;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebResponse;

/**
 * @author Maxim Yunusov
 * @version 1.0 29.05.12
 */
public abstract class BasePage extends WebPage {

    private static final long serialVersionUID = -8928036408393549934L;

    private final HTMLTagModifier htmlTagModifier = new HTMLTagModifier();

    public BasePage() {
        add(new Label("application.title", applicationTitle()));
        final WebMarkupContainer description = new WebMarkupContainer("application.description");
        add(description);
        description.add(new AttributeModifier("content", applicationDescription()));
    }

    protected abstract Model<String> applicationTitle();

    protected abstract Model<String> applicationDescription();

    /**
     * @see org.apache.wicket.MarkupContainer#onRender()
     */
    @Override
    protected void onRender() {
        // Configure the response such as headers etc.
        configureResponse((WebResponse) RequestCycle.get().getResponse());
        final MarkupStream markupStream = new MarkupStream(getMarkup());
        htmlTagModifier.updateHTMLTag(markupStream);
        renderAll(markupStream, null);
    }

}
