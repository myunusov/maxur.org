package org.maxur.commons.component.behavior;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.behavior.Behavior;
import org.maxur.commons.view.api.OSGiWebApplication;
import org.maxur.commons.view.api.ResourcesBehavior;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>5/15/12</pre>
 */
public class BaseResourcesBehavior extends Behavior implements ResourcesBehavior {
    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -3173587755653284922L;


    protected boolean isDeploymentMode() {
        return RuntimeConfigurationType.DEPLOYMENT.equals(Application.get().getConfigurationType());
    }

    @Override
    public void bind(final Component component) {
        final Application application = Application.get();
        if (application instanceof OSGiWebApplication) {
            ((OSGiWebApplication) application).registersResource(this);
        }
        super.bind(component);
    }

    @Override
    public Behavior asBehavior() {
        return this;
    }
}
