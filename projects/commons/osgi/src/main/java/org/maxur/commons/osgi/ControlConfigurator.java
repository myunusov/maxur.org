package org.maxur.commons.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static com.google.inject.name.Names.named;

/**
 * @author Maxim Yunusov
 * @version 1.0 22.05.12
 */
public final class ControlConfigurator implements ManagedService {

    private static final Dictionary NULL_PROPERTIES = new Hashtable();

    private final Logger logger = LoggerFactory.getLogger(ControlConfigurator.class);

    private final Dictionary<String,String> serviceProperties;

    private ServiceRegistration registration;

    private final String pid;

    private final ConfiguratorModule configuratorModule = new ConfiguratorModule();


    public static ControlConfigurator init(final String pid) {
        return new ControlConfigurator(pid);
    }

    private ControlConfigurator(final String pid) {
        this.pid = pid;
        serviceProperties = new Hashtable<>();
        serviceProperties.put(Constants.SERVICE_PID, pid);
    }

    public ControlConfigurator start(final BundleContext bc) {
        this.registration = bc.registerService(
                ManagedService.class.getName(),
                this,
                this.serviceProperties
        );
        MutableInjectorHolder.addModule(this.pid, this.configuratorModule);
        return this;
    }

    public void stop() {
        registration.unregister();
    }

    public void updated(final Dictionary properties) throws ConfigurationException {
        log(properties == null ? NULL_PROPERTIES : properties);
        configuratorModule.setProperties(properties);
    }

    private void log(Dictionary newProperties) {
        logger.info("Configuration has been updated");
        final Enumeration keys = newProperties.keys();
        while (keys.hasMoreElements()) {
            final String key = keys.nextElement().toString();
            final String value = newProperties.get(key).toString();
            logger.debug(String.format("New configuration record: %s = %s", key, value));
        }
    }


    private static final class ConfiguratorModule extends MutableModule {

        private Map<String, String> properties = new HashMap<>();

        @Override
        protected void configure() {
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                if (!Constants.SERVICE_PID.equals(entry.getKey())) {
                    bindConstant().annotatedWith(named(entry.getKey())).to(entry.getValue());
                }
            }
        }

        public void setProperties(Dictionary properties) {
            this.properties.clear();
            if  (properties != null) {
                final Enumeration keys = properties.keys();
                while (keys.hasMoreElements()) {
                    final String key = keys.nextElement().toString();
                    if (Constants.SERVICE_PID.equals(key)) {
                        continue;
                    }
                    final String value = properties.get(key).toString();
                    this.properties.put(key, value);
                }
            }
            update();
        }
    }
}
