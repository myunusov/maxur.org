package org.maxur.adapter.kickstrap;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author Maxim Yunusov
 * @version 1.0 31.05.12
 */
public class JavaScriptPanel extends Panel {

    private static final long serialVersionUID = -1842538566677057959L;

    public JavaScriptPanel(final String id) {
        super(id);
        setRenderBodyOnly(true);
    }

}
