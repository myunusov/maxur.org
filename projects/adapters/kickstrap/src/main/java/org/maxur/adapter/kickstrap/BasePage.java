package org.maxur.adapter.kickstrap;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebResponse;
import org.maxur.commons.component.behavior.NullBehavior;
import org.maxur.commons.view.api.StyleBehavior;

/**
 * @author Maxim Yunusov
 * @version 1.0 29.05.12
 */
public abstract class BasePage extends WebPage {

    private static final long serialVersionUID = -8928036408393549934L;

    private static final String FAVICON_ICO = "favicon.ico";

    @Inject
    private HTMLTagModifier htmlTagModifier;

    @Inject
    @Named("theme")
    private StyleBehavior styleBehavior;

    public BasePage() {
        add(new Label("application.title", applicationTitle()));
        add(new ApplicationDescription("application.description", applicationDescription()));
        add(new FaviconLink("favicon", faviconHref()));
        add(new KickstrapCoreBehavior());
        add(getStyleBehavior());
        add(new JavaScriptPanel("java_script"));
    }

    private Behavior getStyleBehavior() {
        return styleBehavior != null ? styleBehavior.asBehavior() : NullBehavior.get();
    }

    protected Model<String> faviconHref() {
        return new Model<>(FAVICON_ICO);
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
