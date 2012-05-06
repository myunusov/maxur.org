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

    /**
     * <p>Constructor for PageUpdate.</p>
     *
     * @param targetPage a {@link java.lang.Class} object.
     */
    public PageUpdate(Class<? extends IRequestablePage> targetPage) {
        this.targetPage = targetPage;
    }

    /**
     * <p>Getter for the field <code>targetPage</code>.</p>
     *
     * @return a {@link java.lang.Class} object.
     */
    public Class<? extends IRequestablePage> getTargetPage() {
        return targetPage;
    }
}
