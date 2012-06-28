package org.maxur.commons.osgi.configuration;

import org.maxur.commons.core.api.BaseObservable;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Maxim Yunusov
 * @version 1.0 08.06.12
 */
public class Properties extends BaseObservable implements Iterable<Map.Entry<String, String>> {

    private final Logger logger = LoggerFactory.getLogger(Properties.class);

    private Map<String, String> properties = new HashMap<>();

    public void setProperties(final Dictionary properties) {
        logger.info("Configuration has been updated");
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
            log();
        }
        update();
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return properties.entrySet().iterator();
    }

    private void log() {
        for (Map.Entry<String, String> entry : this) {
            logger.debug(String.format("New configuration record: %s = %s", entry.getKey(), entry.getValue()));
        }
    }

}
