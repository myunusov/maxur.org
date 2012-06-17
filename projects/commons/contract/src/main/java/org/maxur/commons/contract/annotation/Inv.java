package org.maxur.commons.contract.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Maxim Yunusov
 * @version 1.0 17.06.12
 */
@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.METHOD)
public @interface Inv {

}


