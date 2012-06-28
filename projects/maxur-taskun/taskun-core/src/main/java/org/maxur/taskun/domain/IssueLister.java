package org.maxur.taskun.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 20.05.12
 */
public interface IssueLister extends Serializable {

    List<Issue> listActive();

}
