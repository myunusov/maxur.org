package org.maxur.commons.component.command;

import org.apache.wicket.request.component.IRequestableComponent;

import java.io.Serializable;

/**
 * <p>Abstract Command class.</p>
 *
 * This interface is used to define commands for Wicket components.
 *
 * see Gof pattern 'Command'
 *
 * @author Maxim Yunusov
 * @version 1.0 15.04.12
 */
public abstract class Command implements Serializable {

    private static final long serialVersionUID = -8326046821326458236L;

    private String titleKey;

    private IRequestableComponent component;

    /**
     * <p>Constructor for Command.</p>
     *
     */
    public Command() {
    }

    /**
     * <p>execute</p>
     *
     * Executes the command.
     */
    public abstract void execute();

    /**
     * <p>bind</p>
     * Bind command with Wicket Component.
     *
     * @param component a {@link org.apache.wicket.markup.html.WebComponent} object.
     */
    public void bind(final IRequestableComponent component) {
        this.component = component;
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
     * @return true if command is Active (is running for example).
     */
    public boolean isActive() {
        return false;
    }

    /**
     * <p>Getter for the field <code>component</code>.</p>
     *
     * @return a {@link org.apache.wicket.markup.html.WebComponent} object.
     */
    public IRequestableComponent getComponent() {
        return component;
    }
}
