package org.maxur.commons.view.api;

import org.apache.wicket.Page;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>6/25/12</pre>
 */
public interface PageLister<P extends Page> {

    Class<? extends P> getLoginPage();

    Class<? extends P> getStartPage();

    Class<? extends P> getInternalErrorPage();

    Class<? extends P> getPageExpiredErrorPage();

    Class<? extends P> getAccessDeniedPage();

    Class<? extends P> getPageNotFoundPage();

}
