package org.maxur.adapter.yaml4;

import org.junit.runners.model.InitializationError;

/**
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class MaxurGuiceTestRunner extends GuiceTestRunner {

    public MaxurGuiceTestRunner(Class<?> classToRun) throws InitializationError {
        super(classToRun, new TestModule());
    }
}