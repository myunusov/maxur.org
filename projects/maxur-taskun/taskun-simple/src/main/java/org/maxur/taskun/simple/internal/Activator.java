package org.maxur.taskun.simple.internal;

import org.maxur.taskun.domain.IssueProvider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Extension of the default OSGi bundle activator.
 *
 * @author Maxim Yunusov
 * @version 1.0 20.05.12
 */
@SuppressWarnings("UnusedDeclaration")
public class Activator implements BundleActivator {

    private static final Logger logger = LoggerFactory.getLogger(Activator.class);

    private ServiceRegistration[] registrations = new ServiceRegistration[2];


    public void start(final BundleContext context) {
        logger.debug("STARTING org.maxur.taskun.simple");
        this.registrations[0] = context.registerService(IssueProvider.class.getName(), new IssueProviderImpl(), null);
        this.registrations[1] = context.registerService(IssueProvider.class.getName(), new DefectProviderImpl(), null);
    }

    public void stop(final BundleContext context) {
        for (ServiceRegistration registration : registrations) {
            registration.unregister();
        }
        logger.debug("STOPPING org.maxur.taskun.simple");
    }


}
