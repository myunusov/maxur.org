package org.maxur.commons.it;

/**
 * @author Maxim Yunusov
 * @version 1.0 25.09.11
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.commons.domain.ExampleService;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Inject;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4TestRunner.class)
public class CommonsBundleIT {

    @Inject
    private BundleContext context;

    @Test
    public void bundleContextShouldNotBeNull() throws Exception {
        assertNotNull(context);
    }

    @Test
    public void messageShouldBeLogged() throws Exception {
        final Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("MyActivator is started.");
    }


    @Test
    public void testFeatures() throws Exception {
        final ExampleService service = retrieveExampleService();
        service.scramble("");
    }

    @Configuration
    public static Option[] configuration() throws Exception {
        return CoreOptions.options(
                CoreOptions.equinox(),
                CoreOptions.felix(),
                CoreOptions.provision(
                        CoreOptions.mavenBundle().groupId("org.ops4j.pax.logging").artifactId("pax-logging-api"),
                        CoreOptions.mavenBundle().groupId("org.maxur").artifactId("maxur-commons-core")
                )
        );
    }

    private ExampleService retrieveExampleService() throws InterruptedException {
        ServiceTracker tracker = new ServiceTracker(context, ExampleService.class.getName(), null);
        tracker.open();
        ExampleService service = (ExampleService) tracker.waitForService(5000);
        tracker.close();
        assertNotNull(service);
        return service;
    }


}