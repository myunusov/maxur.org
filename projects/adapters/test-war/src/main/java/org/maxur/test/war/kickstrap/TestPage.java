package org.maxur.test.war.kickstrap;

import org.apache.wicket.model.Model;
import org.maxur.adapter.kickstrap.BasePage;

/**
 * @author Maxim Yunusov
 * @version 1.0 29.05.12
 */
public class TestPage extends BasePage {

    private static final long serialVersionUID = 2120130347525861141L;

    public TestPage() {
        super();
        add(new TestPanel("header"));
        add(new FooterPanel("footer"));
    }

    protected Model<String> applicationTitle() {
        return new Model<>("Test Application");
    }

    @Override
    protected Model<String> applicationDescription() {
        return new Model<>("Test Application");
    }
}

