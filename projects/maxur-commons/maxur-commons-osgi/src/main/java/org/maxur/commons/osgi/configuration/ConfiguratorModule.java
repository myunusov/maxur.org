package org.maxur.commons.osgi.configuration;

import org.maxur.commons.core.api.Observer;
import org.maxur.commons.osgi.base.MutableModule;
import org.osgi.framework.Constants;

import java.util.Map;

import static com.google.inject.name.Names.named;

/**
* @author Maxim Yunusov
* @version 1.0 08.06.12
*/
final class ConfiguratorModule extends MutableModule implements Observer {

    private final Properties properties;

    public ConfiguratorModule(final Properties properties) {
        this.properties = properties;
        properties.addObserver(this);
    }

    @Override
    protected void configure() {
        for (Map.Entry<String, String> entry : properties) {
            if (!Constants.SERVICE_PID.equals(entry.getKey())) {
                bindConstant().annotatedWith(named(entry.getKey())).to(entry.getValue());
            }
        }
    }

}
