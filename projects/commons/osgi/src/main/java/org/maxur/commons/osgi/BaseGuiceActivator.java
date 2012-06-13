package org.maxur.commons.osgi;

import org.maxur.commons.osgi.base.MutableInjectorHolder;
import org.maxur.commons.osgi.configuration.ControlConfigurator;
import org.maxur.commons.osgi.providers.BaseServiceManager;
import org.maxur.commons.osgi.providers.ControlProviders;
import org.maxur.commons.osgi.services.ControlServices;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

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
        doStop();
        MutableInjectorHolder.start(pid);
        doInit();
        config();
        doStart(bc);

    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop(final BundleContext bc) {
        doStop();
        MutableInjectorHolder.stop(pid);
        logger.debug("STOPPING {}", pid);
    }


    private void doStart(BundleContext bc) {
        controlConfigurator.start(bc);
        controlProviders.start(bc);
        controlServices.start(bc);
    }

    private void doInit() {
        controlServices = ControlServices.make(pid);
        controlProviders = ControlProviders.make(pid);
        controlConfigurator = ControlConfigurator.make(pid);
    }

    private void doStop() {
        if (controlServices != null) {
            controlServices.stop();
        }
        if (controlProviders != null) {
            controlProviders.stop();
        }
        if (controlConfigurator != null) {
            controlConfigurator.stop();
        }
    }

    protected abstract void config();

    protected Binder bind(final Class<?> providedClass) {
        return new Binder(providedClass, controlProviders);
    }

    public Exporter export(final Object service) {
        return new Exporter(service, controlServices);
    }

    public static final class Binder {

        private final Class<?> providedClass;

        private final ControlProviders controlProviders;

        private boolean isMultiple = false;

        private Annotation annotation;

        private Binder(final Class<?> providedClass, final ControlProviders controlProviders) {
            this.providedClass = providedClass;
            this.controlProviders = controlProviders;
        }

        public Binder single() {
            this.isMultiple = false;
            return this;
        }

        public Binder multiple() {
            this.isMultiple = true;
            return this;
        }

        public Binder annotatedWith(final Annotation annotation) {
            this.annotation = annotation;
            return this;
        }

        public void toOSGiService() {
            controlProviders.addServiceManager(new BaseServiceManager<>(providedClass, isMultiple, annotation));
        }
    }

    public static final class Exporter {

        private final Object service;

        private final ControlServices controlServices;

        private Annotation annotation;

        public Exporter(final Object service, final ControlServices controlServices) {
            this.service = service;
            this.controlServices = controlServices;
        }

        public Exporter annotatedWith(final Annotation annotation) {
            this.annotation = annotation;
            return this;
        }

        public void asOSGiService(final Class<?> servesClass) {
            controlServices.bind(servesClass, service, annotation);
        }
    }


}
