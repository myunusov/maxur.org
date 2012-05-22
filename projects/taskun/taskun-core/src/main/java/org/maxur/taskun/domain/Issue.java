package org.maxur.taskun.domain;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0 20.05.12
 */
public abstract class Issue implements Serializable {

    private static final long serialVersionUID = 2776107640309129315L;

    private final String description;

    private boolean active;

    public Issue(final String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public String getDescription() {
        return description;
    }
}
