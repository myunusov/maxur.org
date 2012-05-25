package org.maxur.taskun.domain.internal;

import org.maxur.commons.osgi.BaseGuiceActivator;
import org.maxur.taskun.domain.IssueLister;
import org.maxur.taskun.domain.IssueProvider;

/**
 * Extension of the default OSGi bundle activator.
 *
 * @author Maxim Yunusov
 * @version 1.0 20.05.12
 */
@SuppressWarnings("UnusedDeclaration")
public class TrackingIssueListerActivator extends BaseGuiceActivator {

    public static final String PID = "org.maxur.taskun.domain";

    public TrackingIssueListerActivator() {
        super(PID);
    }

    @Override
    protected void config() {
        bindMultiple(IssueProvider.class);
        export(IssueLister.class, new IssueListerImpl());
    }

}
