package org.maxur.taskun.domain;

import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 20.05.12
 */
public interface IssueLister {

    List listActive(String director);

}
