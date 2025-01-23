package com.xdozhao.iptv.common.mq.rabbit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 消息自动确认
 * <p>
 * 用于方法，并且方法参数为 Channel,Message
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 09:42
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AutoConfirmation {
    boolean enable() default true;
}
