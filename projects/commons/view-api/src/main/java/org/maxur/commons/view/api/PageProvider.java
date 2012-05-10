package org.maxur.commons.view.api;

import org.apache.wicket.markup.html.WebPage;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>5/10/12</pre>
 */
public interface PageProvider {

    Class<? extends WebPage> get();
}
