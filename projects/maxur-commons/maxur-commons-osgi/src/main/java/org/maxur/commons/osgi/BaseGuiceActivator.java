package org.maxur.commons.osgi;

import org.maxur.commons.osgi.base.CompositeManager;
import org.maxur.commons.osgi.holder.InjectorHolderList;
import org.maxur.commons.osgi.configuration.ConfigManager;
import org.maxur.commons.osgi.providers.Binder;
import org.maxur.commons.osgi.providers.ProvidersGroup;
import org.maxur.commons.osgi.providers.ServiceTrackersHolder;
import org.maxur.commons.osgi.services.Exporter;
import org.maxur.commons.osgi.services.ServiceManager;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Maxim Yunusov
 * @version 1.0 23.05.12
 */
public abstract class BaseGuiceActivator implements BundleActivator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private CompositeManager<ProvidersGroup> providerGroupsManager = new CompositeManager<>();

    private CompositeManager<ServiceManager> servicesManager = new CompositeManager<>();

    private ConfigManager configManager = new ConfigManager();

    private ServiceTrackersHolder trackersHolder = new ServiceTrackersHolder();

    private final String pid;

    protected BaseGuiceActivator(final String pid) {
        this.pid = pid;
    }

    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start(final BundleContext bc) {
        logger.debug("STARTING {}", pid);
        doStop();
        // TODO PID must be unique
        InjectorHolderList.start(pid);
        config();
        doStart(bc);
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop(final BundleContext bc) {
        doStop();
        InjectorHolderList.stop(pid);
        logger.debug("STOPPING {}", pid);
    }

    private void doStart(BundleContext bc) {
        configManager.start(bc, pid);
        providerGroupsManager.start(bc, pid);
        servicesManager.start(bc, pid);
        trackersHolder.init(bc, providerGroupsManager.get());
        trackersHolder.start(bc, pid);
    }

    private void doStop() {
        trackersHolder.stop();
        servicesManager.stop();
        providerGroupsManager.stop();
        configManager.stop();
    }

    protected abstract void config();

    protected Binder bind(final Class<?> providedClass) {
        return new Binder(providerGroupsManager, providedClass);
    }

    protected Exporter export(final Object service) {
        return new Exporter(servicesManager, service);
    }

}
