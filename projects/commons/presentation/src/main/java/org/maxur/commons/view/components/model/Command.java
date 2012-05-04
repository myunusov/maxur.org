package org.maxur.commons.view.components.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebComponent;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0 15.04.12
 */
public abstract class Command implements Serializable {

    private static final long serialVersionUID = -8326046821326458236L;

    private boolean ajax;

    private String titleKey;

    private WebComponent component;

    public abstract void execute();

    public abstract void execute(final AjaxRequestTarget target);

    public Command(boolean ajax) {
        this.ajax = ajax;
    }

    public boolean isAjax() {
        return ajax;
    }

    public String getTitleKey() {
        return titleKey;
    }

    protected void setTitleKey(final String titleKey) {
        this.titleKey = titleKey;
    }

    public boolean isActive() {
        return false;
    }

    public void bind(final WebComponent component) {
        this.component = component;
    }

    public WebComponent getComponent() {
        return component;
    }
}