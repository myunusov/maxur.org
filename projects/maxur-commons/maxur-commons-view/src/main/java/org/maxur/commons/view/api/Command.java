package org.maxur.commons.view.api;

import org.apache.wicket.request.component.IRequestableComponent;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>5/10/12</pre>
 */
public interface Command extends Serializable {
    /**
     * <p>execute</p>
     *
     * Executes the command.
     */
    void execute();

    void bind(IRequestableComponent component);

    String getTitleKey();

    boolean isActive();
}
