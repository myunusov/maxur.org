package org.maxur.commons.osgi;

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

    private ControlProviders controlProviders;

    private ControlConfigurator controlConfigurator;

    private ControlServices controlServices;

    private final String pid;

    protected BaseGuiceActivator(final String pid) {
        this.pid = pid;
    }

    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start(final BundleContext bc) {
        logger.debug("STARTING {}", pid);
        MutableInjectorHolder.start(pid);
        controlConfigurator = ControlConfigurator
                .init(pid)
                .start(bc);

        if (controlProviders != null) {
            controlProviders.stop();
        }

        controlServices  = ControlServices.init();
        controlProviders = ControlProviders.init(pid);
        config();
        controlProviders.start(bc, pid);
        controlServices.start(bc, pid);
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop(final BundleContext bc) {
        controlServices.stop();
        controlConfigurator.stop();
        controlProviders.stop();
        MutableInjectorHolder.stop(pid);
        logger.debug("STOPPING {}", pid);
    }

    protected abstract void config();

    public void bindSingle(final Class<?> providedClass) {
        controlProviders.bindSingle(providedClass);
    }

    public void bindMultiple(final Class<?> providedClass) {
        controlProviders.bindMultiple(providedClass);
    }

    public void export(final Class<?> servesClass, final Object service) {
        controlServices.bind(servesClass, service);
    }

}
