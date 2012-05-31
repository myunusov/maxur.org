package org.maxur.test.war;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author Maxim Yunusov
 * @version 1.0 29.05.12
 */
public class TestPanel extends Panel {

    private static final long serialVersionUID = 5967310711395309690L;

    public TestPanel(final String id) {
        super(id);
        add(new Label("message", "Hello World!"));
    }
}
