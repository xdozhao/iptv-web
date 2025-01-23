package com.xdozhao.iptv.common.mq.rabbit.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 09:48
 */
@Slf4j
@Component
public class DefaultConfirmCallback implements RabbitTemplate.ConfirmCallback {
    /**
     * Confirmation callback.
     *
     * @param correlationData correlation data for the callback.
     * @param ack             true for ack, false for nack
     * @param cause           An optional cause, for nack, when available, otherwise null.
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // 没找到交换机 ack false
        // 找到交换机 ack true
        if (ack) {
            log.debug("Rabbit confirm callback <<<<<<< ack:{},cause:{},data:{},", ack, cause, correlationData);
        } else {
            log.error("Rabbit confirm callback <<<<<<< ack:{},cause:{},data:{},", ack, cause, correlationData);
        }
    }
}
