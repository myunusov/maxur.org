package org.maxur.taskun.war.config;

import org.maxur.commons.component.osgi.WebBundleContext;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author Maxim Yunusov
 * @version 1.0 13.05.12
 */
public class ControlManager {

    private static final Logger logger = LoggerFactory.getLogger(ControlManager.class);

    private Dictionary properties;

    private final ControlConfigurator configurator = new ControlConfigurator();

    public void setProperties(final Dictionary properties) {
        this.properties = properties;
    }

    protected class ControlConfigurator implements ManagedService {

        public void updated(final Dictionary properties) throws ConfigurationException {
            logger.info("Configuration has been updated");
            setProperties(properties == null ? makeDefaultConfiguration() : properties);
            updateService();
        }

        private Dictionary makeDefaultConfiguration() {
            return new Hashtable();
        }

    }

    private void updateService() {
        WebBundleContext.setProperties(properties);
        final Enumeration keys = properties.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = properties.get(key);
            logger.debug(String.format("New configuration record: %s = %s", key, value));
        }
    }

    public Object getManagedService() {
        return configurator;
    }

}
