package com.practice.spring.annotation;

import java.lang.annotation.*;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO
 * @date 2018-04-30
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {

    String value() default "";
}
