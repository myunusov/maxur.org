package org.maxur.commons.view.api;

import org.apache.wicket.behavior.Behavior;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public interface StyleBehavior extends Serializable {

    Behavior asBehavior();

}
