package org.maxur.taskun.domain;

/**
 * @author Maxim Yunusov
 * @version 1.0 21.05.12
 */
public class BaseDefect extends Issue {

    private static final long serialVersionUID = -4889760459273830044L;

    public BaseDefect(final String description) {
        super(description);
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean isTask() {
        return false;
    }

    @Override
    public boolean isDefect() {
        return true;
    }
}
