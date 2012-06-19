package org.maxur.commons.osgi.configuration;

import org.maxur.commons.core.utils.DictionaryUtils;
import org.maxur.commons.osgi.holder.InjectorHolderList;
import org.maxur.commons.osgi.base.OSGiManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;

import java.util.Dictionary;

/**
 * @author Maxim Yunusov
 * @version 1.0 22.05.12
 */
public final class ConfigManager implements OSGiManager, ManagedService {

    private final Properties properties = new Properties();

    private ServiceRegistration registration = null;

    public void start(final BundleContext bc, final String pid) {
        this.registration = bc.registerService(
                ManagedService.class.getName(),
                this,
                DictionaryUtils.singleton(Constants.SERVICE_PID, pid)
        );
        InjectorHolderList.holder(pid).addModule(new ConfiguratorModule(this.properties));
    }

    public void stop() {
        if (null != registration) {
            this.registration.unregister();
            registration = null;
        }
    }

    @Override
    public void updated(final Dictionary properties) throws ConfigurationException {
        this.properties.setProperties(properties);
    }

}
