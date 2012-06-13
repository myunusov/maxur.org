package org.maxur.commons.osgi.configuration;

import org.maxur.commons.core.utils.DictionaryUtils;
import org.maxur.commons.osgi.base.MutableInjectorHolder;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;

import java.util.Dictionary;

import static org.maxur.commons.core.assertion.Assert.error;
import static org.maxur.commons.core.assertion.Assert.check;

/**
 * @author Maxim Yunusov
 * @version 1.0 22.05.12
 */
public final class ControlConfigurator implements ManagedService {

    private ServiceRegistration registration = null;

    private final Properties properties;

    private final String pid;

    public static ControlConfigurator make(final String pid) {
        return new ControlConfigurator(pid);
    }

    private ControlConfigurator(final String pid) {
        this.pid = pid;
        this.properties = new Properties();
    }

    public ControlConfigurator start(final BundleContext bc) {
        this.registration = bc.registerService(
                ManagedService.class.getName(),
                this,
                DictionaryUtils.singleton(Constants.SERVICE_PID, pid)
        );
        MutableInjectorHolder.addModule(pid, new ConfiguratorModule(this.properties));
        return this;
    }

    public void stop() {
        check(registration).notNull().ifNot(error("ControlConfigurator: 'start' must be called before 'stop'"));
        this.registration.unregister();
    }

    @Override
    public void updated(final Dictionary properties) throws ConfigurationException {
        this.properties.setProperties(properties);
    }

}
