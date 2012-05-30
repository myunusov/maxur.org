package org.maxur.test.war;

import org.apache.wicket.markup.MarkupElement;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.parser.XmlPullParser;
import org.apache.wicket.markup.parser.XmlTag;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.maxur.commons.component.model.webclient.BaseWebBrowserDetector;
import org.maxur.commons.component.model.webclient.WebBrowser;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.component.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;

import static org.maxur.commons.component.model.webclient.WebBrowserUtils.ie;


/**
 * see paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither
 */
public class HTMLTagModifier implements Serializable {

    private static final long serialVersionUID = 2363569346974205992L;

    private static final String DOCTYPE_HTML = "<!doctype html>";
    private static final String HTML_TAG = "html";
    private static final String CLASS_ATTRIBUTE = "class";
    private static final String ATTRIBUTE_VALUE_SEPARATOR = " ";

    private WebBrowserDetector detector = new BaseWebBrowserDetector();

    public HTMLTagModifier() {
    }

    public void updateHTMLTag(final MarkupStream markupStream) {
        // Remember where we are
        final int index = markupStream.getCurrentIndex();
        boolean result = false;
        do {
            final MarkupElement element = markupStream.get();
            try {
                result = processElement(element);
            } catch (IOException | ResourceStreamNotFoundException | ParseException ignore) {
                break;
            }
            if (!markupStream.hasMore()) {
                break;
            }
            markupStream.next();
        } while (!result);
        if (!result) {
            // Go back to where we were and move the markup stream forward to whatever the next
            // element is.
            markupStream.setCurrentIndex(index);
        }
    }

    private boolean processElement(final MarkupElement element)
            throws IOException, ResourceStreamNotFoundException, ParseException {
        final XmlPullParser parser = new XmlPullParser();
        parser.parse(element.toCharSequence().toString().trim());
        final XmlTag nextTag = parser.nextTag();
        if (nextTag.getName().equalsIgnoreCase(HTML_TAG)) {
            final String attribute = nextTag.getAttribute(CLASS_ATTRIBUTE).toString();
            nextTag.put(CLASS_ATTRIBUTE, StringUtils.merge(ATTRIBUTE_VALUE_SEPARATOR, attribute, getClasses()));
            // write the highlighted XHTML in between the code tags
            final Response response = RequestCycle.get().getResponse();
            response.write(DOCTYPE_HTML);
            response.write(nextTag.toCharSequence());
            response.write("\n");
            return true;
        }
        return false;
    }

    private String getClasses() {
        final WebBrowser browser = detector.detect(getHttpServletRequest());
        final StringBuilder builder = new StringBuilder("no-js");
        if (browser.lt(ie(9))) {
            builder.append(ATTRIBUTE_VALUE_SEPARATOR).append("lt-ie9");
        }
        if (browser.lt(ie(8))) {
            builder.append(ATTRIBUTE_VALUE_SEPARATOR).append("lt-ie8");
        }
        if (browser.lt(ie(7))) {
            builder.append(ATTRIBUTE_VALUE_SEPARATOR).append("lt-ie7");
        }
        return builder.toString();
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest());
    }

}
