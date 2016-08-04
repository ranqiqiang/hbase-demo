package com.dfire.annocation;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * Row unit Key
 * Created by qqr on 16/8/3.
 */
@java.lang.annotation.Target({ElementType.FIELD})
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
public @interface RowKey {
}
