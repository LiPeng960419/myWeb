package com.lipeng.common.aop;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @Author: lipeng 910138
 * @Date: 2020/10/13 15:40
 */
@Documented
@Retention(RUNTIME)
@Target({ElementType.METHOD})
public @interface PageAble {

    String clazzName();

}