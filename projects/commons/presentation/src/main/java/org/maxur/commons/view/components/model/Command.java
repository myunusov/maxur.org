package org.maxur.commons.view.components.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebComponent;

import java.io.Serializable;

/**
 * <p>Abstract Command class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 15.04.12
 */
public abstract class Command implements Serializable {

    private static final long serialVersionUID = -8326046821326458236L;

    private boolean ajax;

    private String titleKey;

    private WebComponent component;

    /**
     * <p>execute</p>
     */
    public abstract void execute();

    /**
     * <p>execute</p>
     *
     * @param target a {@link org.apache.wicket.ajax.AjaxRequestTarget} object.
     */
    public abstract void execute(final AjaxRequestTarget target);

    /**
     * <p>Constructor for Command.</p>
     *
     * @param ajax a boolean.
     */
    public Command(boolean ajax) {
        this.ajax = ajax;
    }

    /**
     * <p>isAjax</p>
     *
     * @return a boolean.
     */
    public boolean isAjax() {
        return ajax;
    }

    /**
     * <p>Getter for the field <code>titleKey</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getTitleKey() {
        return titleKey;
    }

    /**
     * <p>Setter for the field <code>titleKey</code>.</p>
     *
     * @param titleKey a {@link java.lang.String} object.
     */
    protected void setTitleKey(final String titleKey) {
        this.titleKey = titleKey;
    }

    /**
     * <p>isActive</p>
     *
     * @return a boolean.
     */
    public boolean isActive() {
        return false;
    }

    /**
     * <p>bind</p>
     *
     * @param component a {@link org.apache.wicket.markup.html.WebComponent} object.
     */
    public void bind(final WebComponent component) {
        this.component = component;
    }

    /**
     * <p>Getter for the field <code>component</code>.</p>
     *
     * @return a {@link org.apache.wicket.markup.html.WebComponent} object.
     */
    public WebComponent getComponent() {
        return component;
    }
}
