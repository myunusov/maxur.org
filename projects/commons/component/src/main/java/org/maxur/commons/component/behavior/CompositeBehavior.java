package org.maxur.commons.component.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.behavior.IBehaviorListener;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.maxur.commons.component.utils.SerializableArrayList;
import org.maxur.commons.component.utils.SerializableList;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Represents a composite behavior allowing the user to attach multiple behaviors to a
 * component at once.
 *
 * @author Maxim Yunusov
 * @version 1.0 11.05.12
 */
public class CompositeBehavior extends Behavior {
    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = 2172668659738903804L;

    private final SerializableList<Behavior> behaviors;

    public CompositeBehavior(final Behavior... behaviors) {
        this.behaviors = new SerializableArrayList<>(Arrays.asList(behaviors));
    }

    /**
     * Called when a component is about to render.
     *
     * @param component
     *            the component that has this behavior coupled
     */
    public void beforeRender(Component component) {
        for (Behavior behavior : behaviors) {
            behavior.beforeRender(component);
        }
    }

    /**
     * Called when a component that has this behavior coupled was rendered.
     *
     * @param component the component that has this behavior coupled
     */
    public void afterRender(Component component) {
        for (Behavior behavior : behaviors) {
            behavior.afterRender(component);
        }
    }

    /**
     * Bind this handler to the given component. This method is called by the host component
     * immediately after this behavior is added to it. This method is useful if you need to do
     * initialization based on the component it is attached and you can't wait to do it at render
     * time. Keep in mind that if you decide to keep a reference to the host component, it is not
     * thread safe anymore, and should thus only be used in situations where you do not reuse the
     * behavior for multiple components.
     *
     * @param component the component to bind to
     */
    public void bind(Component component) {
        for (Behavior behavior : behaviors) {
            behavior.bind(component);
        }
    }

    /**
     * Notifies the behavior it is removed from the specified component
     *
     * @param component the component this behavior is unbound from
     */
    public void unbind(Component component) {
        for (Behavior behavior : behaviors) {
            behavior.unbind(component);
        }
    }

    /**
     * Allows the behavior to detach any state it has attached during request processing.
     *
     * @param component the component that initiates the detachment of this behavior
     */
    public void detach(Component component) {
        for (Behavior behavior : behaviors) {
            behavior.detach(component);
        }
    }

    /**
     * In case an unexpected exception happened anywhere between
     * {@linkplain #onComponentTag(org.apache.wicket.Component, org.apache.wicket.markup.ComponentTag)} and
     * {@linkplain #afterRender(org.apache.wicket.Component)},
     * onException() will be called for any behavior. Typically, if you clean up resources in
     * {@link #afterRender(Component)}, you should do the same in the implementation of this method.
     *
     * @param component
     *            the component that has a reference to this behavior and during which processing
     *            the exception occurred
     * @param exception
     *            the unexpected exception
     */
    public void onException(Component component, RuntimeException exception) {
        for (Behavior behavior : behaviors) {
            behavior.onException(component, exception);
        }
    }

    /**
     * This method returns false if the behavior generates a callback url (for example ajax
     * behaviors)
     *
     * @param component the component that has this behavior coupled.
     *
     * @return boolean true or false.
     */
    public boolean getStatelessHint(Component component) {
        for (Behavior behavior : behaviors) {
            // this behavior implements a callback interface, so it cannot be stateless
            if (behavior instanceof IBehaviorListener) {
                return false;
            }
        }
        return true;
    }

    /**
     * Called when a components is rendering and wants to render this behavior. If false is returned
     * this behavior will be ignored.
     *
     * @param component the component that has this behavior coupled
     *
     * @return true if this behavior must be executed/rendered
     */
    public boolean isEnabled(Component component) {
        return true;
    }

    /**
     * Called any time a component that has this behavior registered is rendering the component tag.
     *
     * @param component the component that renders this tag currently
     * @param tag the tag that is rendered
     */
    public void onComponentTag(Component component, ComponentTag tag) {
        for (Behavior behavior : behaviors) {
            behavior.onComponentTag(component, tag);
        }
    }

    /**
     * Specifies whether or not this behavior is temporary. Temporary behaviors are removed at the
     * end of request and never reattached. Such behaviors are useful for modifying component
     * rendering only when it renders next. Use Cases include javascript effects, initial client side
     * dom setup, etc.
     *
     * @param component the component
     *
     * @return true if this behavior is temporary
     */
    public boolean isTemporary(Component component) {
        return false;
    }

    /**
     * Checks whether or not a listener interface can be invoked on this behavior. For further
     * information please read the javadoc on {@link Component#canCallListenerInterface(java.lang.reflect.Method)},
     * this method has the same semantics.
     *
     * WARNING: Read the javadoc of {@link Component#canCallListenerInterface(java.lang.reflect.Method)} for important
     * security-related information.
     *
     * @param component component this behavior is attached to
     * @param method listener method being invoked
     * @return {@literal true} iff the listener method can be invoked
     */
    public boolean canCallListenerInterface(Component component, Method method) {
        return isEnabled(component) && component.canCallListenerInterface(method);
    }


    /**
     * Render to the web response whatever the component wants to contribute to the head section.
     *
     * @param component  the component
     *
     * @param response Response object
     */
    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        for (Behavior behavior : behaviors) {
            behavior.renderHead(component, response);
        }
    }

    /**
     * Called immediately after the onConfigure method in a component. Since this is before the
     * rendering cycle has begun, the behavior can modify the configuration of the component (i.e.
     * setVisible(false))
     *
     * @param component the component being configured
     */
    public void onConfigure(Component component) {
        for (Behavior behavior : behaviors) {
            behavior.onConfigure(component);
        }
    }

    /**
     * Called to notify the behavior about any events sent to the component
     *
     * @see org.apache.wicket.IComponentAwareEventSink#onEvent(org.apache.wicket.Component,
     *      org.apache.wicket.event.IEvent)
     */
    @Override
    public void onEvent(Component component, IEvent<?> event) {
        for (Behavior behavior : behaviors) {
            behavior.onEvent(component, event);
        }
    }



}
