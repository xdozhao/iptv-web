package com.xdozhao.iptv.common.mq.rabbit.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 09:48
 */
@Slf4j
@Component
public class DefaultReturnsCallback implements RabbitTemplate.ReturnsCallback {
    /**
     * Returned message callback.
     *
     * @param returned the returned message and metadata.
     */
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("Returns queue code:{},msg: {},exchange: {},routing key: {}", returned.getReplyCode(), returned.getReplyText(), returned.getExchange(), returned.getRoutingKey());
        log.debug("Rabbit returns callback message. <<<<< MessageProperties: {}", returned.getMessage().getMessageProperties());
        log.debug("Rabbit returns callback message. <<<<< Body: {}", new String(returned.getMessage().getBody()));
    }
}
