package org.maxur.commons.view.components.menu;

import org.apache.wicket.request.component.IRequestablePage;

/**
 * The page update payload.
 *
 * @author Maxim Yunusov
 * @version 1.0 15.04.12
 */
public class PageUpdate {

    private final Class<? extends IRequestablePage> targetPage;

    public PageUpdate(Class<? extends IRequestablePage> targetPage) {
        this.targetPage = targetPage;
    }

    public Class<? extends IRequestablePage> getTargetPage() {
        return targetPage;
    }
}
