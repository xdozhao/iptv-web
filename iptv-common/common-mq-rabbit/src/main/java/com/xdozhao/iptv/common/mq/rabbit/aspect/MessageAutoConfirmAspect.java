package com.xdozhao.iptv.common.mq.rabbit.aspect;

import com.rabbitmq.client.Channel;
import com.xdozhao.iptv.common.mq.rabbit.annotation.AutoConfirmation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 09:45
 */
@Slf4j
@Aspect
@Component
public class MessageAutoConfirmAspect {

    @Around(value = "args(channel,message,..)&&@annotation(autoConfirmation)&&(@annotation(org.springframework.amqp.rabbit.annotation.RabbitListener)||@annotation(org.springframework.amqp.rabbit.annotation.RabbitHandler))",
            argNames = "proceedingJoinPoint,channel,message,autoConfirmation")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, Channel channel, Message message, AutoConfirmation autoConfirmation) {
        Object result = null;
        log.debug("around start.\nMsgInfo: {} \nBody :{}", message.getMessageProperties(), new String(message.getBody(), StandardCharsets.UTF_8));
        try {
            try {
                result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
                if (autoConfirmation.enable()) {
                    // 自动确认启用
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                }
            } catch (Throwable e) {
                log.error("message handler error,auto reject.", e);
                // 消息处理失败,不重新入队列
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            log.error("", e);
        }
        log.debug("around end, Result:{}", result);
        return result;
    }
}
