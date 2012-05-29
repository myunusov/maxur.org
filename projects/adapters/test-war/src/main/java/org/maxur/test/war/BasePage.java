package org.maxur.test.war;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.maxur.commons.component.model.webclient.BaseWebBrowserDetector;
import org.maxur.commons.component.model.webclient.WebBrowser;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;

import javax.servlet.http.HttpServletRequest;

import static org.maxur.commons.component.model.webclient.WebBrowserUtils.ie;

/**
 * @author Maxim Yunusov
 * @version 1.0 29.05.12
 */
public class BasePage extends WebPage {

    private static final long serialVersionUID = -8928036408393549934L;

    public BasePage() {
        final WebMarkupContainer html = new WebMarkupContainer("html");
        add(html);
        html.add(new HTMLTagModifier());
        html.add(new Label("message", "Hello World!"));
    }

    /**
     * paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither
     */
    private static class HTMLTagModifier extends Behavior {

        private static final long serialVersionUID = -1855648821614031565L;

        private final Model<String> model;

        private HTMLTagModifier() {
            model = new HTMLModel();
        }

        @Override
        public void onComponentTag(final Component component, final ComponentTag tag) {
            tag.put("class", model.getObject());
        }

        private static class HTMLModel extends Model<String> {

            private static final long serialVersionUID = -5404834649140812260L;

            private WebBrowserDetector detector = new BaseWebBrowserDetector();

            @Override
            public String getObject() {
                final WebBrowser browser = detector.detect(getHttpServletRequest());
                final StringBuilder builder = new StringBuilder("no-js");
                if (browser.lt(ie(9)))
                    builder.append(" lt-ie9");
                if (browser.lt(ie(8)))
                    builder.append(" lt-ie8");
                if (browser.lt(ie(7)))
                    builder.append(" lt-ie7");
                return builder.toString();
            }

            private HttpServletRequest getHttpServletRequest() {
                return ((HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest());
            }

        }
    }

}
