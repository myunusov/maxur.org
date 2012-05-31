package org.maxur.taskun.domain;

/**
 * @author Maxim Yunusov
 * @version 1.0 21.05.12
 */
public class BaseTask extends Issue {

    private static final long serialVersionUID = -4889760459273830044L;

    public BaseTask(final String description) {
        super(description);
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean isTask() {
        return true;
    }

    @Override
    public boolean isDefect() {
        return false;
    }
}


