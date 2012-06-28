package org.maxur.commons.osgi.holder;

import com.google.inject.Injector;
import org.maxur.commons.core.api.AbstractRefresher;

/**
* @author Maxim Yunusov
* @version 1.0 19.06.12
*/
public class InjectorRefresher extends AbstractRefresher<Injector> {

    private static final long serialVersionUID = -2613436504821318003L;

    private String pid;

    private transient Injector item;

    public InjectorRefresher(final String pid) {
        super();
        this.pid = pid;
    }

    @Override
    public boolean isStale() {
        return super.isStale() || this.item != InjectorHolderList.holder(pid).get();
    }

    @Override
    public Injector get() {
        if (isStale()) {
            this.item = InjectorHolderList.holder(pid).get();
        }
        return this.item;
    }

}
