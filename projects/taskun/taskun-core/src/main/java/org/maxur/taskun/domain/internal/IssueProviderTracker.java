package org.maxur.taskun.domain.internal;

import org.maxur.taskun.domain.IssueLister;
import org.maxur.taskun.domain.IssueProvider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Maxim Yunusov
 * @version 1.0 20.05.12
 */
public class IssueProviderTracker extends ServiceTracker {

    private final IssueListerImpl lister = new IssueListerImpl();

    private int providerCount = 0;

    private ServiceRegistration registration = null;

    private boolean registering = false;

    public IssueProviderTracker(final BundleContext context) {
        super(context, IssueProvider.class.getName(), null);
    }

    public Object addingService(final ServiceReference reference) {
        final IssueProvider provider = (IssueProvider) context.getService(reference);
        lister.bindProvider(provider);

        synchronized(this) {
            providerCount++;
            if (registering)
                return provider;
            registering = (providerCount == 1);
            if (!registering)
                return provider;
        }

        final ServiceRegistration reg = context.registerService(IssueLister.class.getName(), lister, null);

        synchronized(this) {
            registering = false;
            registration = reg;
        }

        return provider;
    }

    public void removedService(final ServiceReference reference, final Object service) {
        final IssueProvider provider = (IssueProvider) service;
        lister.unbindProvider(provider);
        context.ungetService(reference);

        ServiceRegistration needsUnregistration = null;
        synchronized(this) {
            providerCount--;
            if (providerCount == 0) {
                needsUnregistration = registration;
                registration = null;
            }
        }

        if(needsUnregistration != null) {
            needsUnregistration.unregister();
        }
    }
}
