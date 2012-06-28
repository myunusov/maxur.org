package org.maxur.commons.contract;

import org.maxur.commons.contract.annotation.Inv;

/**
 * @author Maxim Yunusov
 * @version 1.0 17.06.12
 */
public class FakeInterceptedService implements InterceptedService {

    @Override
    @Inv
    public void process() {
    }

}
