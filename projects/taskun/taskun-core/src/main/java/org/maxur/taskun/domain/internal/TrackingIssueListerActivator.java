package org.maxur.taskun.domain.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Extension of the default OSGi bundle activator.
 *
 * @author Maxim Yunusov
 * @version 1.0 20.05.12
 */
@SuppressWarnings("UnusedDeclaration")
public class TrackingIssueListerActivator implements BundleActivator {

    private static final Logger logger = LoggerFactory.getLogger(TrackingIssueListerActivator.class);

    private IssueProviderTracker tracker;

    public void start(final BundleContext context) {
        logger.debug("STARTING org.maxur.taskun.domain");
        tracker = new IssueProviderTracker(context);
        tracker.open();
    }

    public void stop(BundleContext context) {
        tracker.close();
        logger.debug("STOPPING org.maxur.taskun.domain");
    }
}
