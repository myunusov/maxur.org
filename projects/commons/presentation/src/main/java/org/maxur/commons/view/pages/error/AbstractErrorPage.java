package org.maxur.commons.view.pages.error;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.maxur.commons.view.pages.BasePage;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>5/10/12</pre>
 */
public class AbstractErrorPage extends BasePage {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = 1140533080422731198L;

    protected void getHomePageLink(String id) {
        new BookmarkablePageLink<Void>(id, getApplication().getHomePage());
    }
}
