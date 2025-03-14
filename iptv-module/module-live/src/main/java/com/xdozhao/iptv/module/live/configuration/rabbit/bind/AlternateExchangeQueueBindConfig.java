package com.xdozhao.iptv.module.live.configuration.rabbit.bind;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/22 10:18
 */
@Configuration
public class AlternateExchangeQueueBindConfig {
    /**
     * 绑定默认备用交换机、队列
     *
     * @param defaultAlternateExchangeQueue
     * @param defaultAlternateExchange
     *
     * @return
     */
    @Bean
    Binding alternateExchangeQueueBind(Queue defaultAlternateExchangeQueue, FanoutExchange defaultAlternateExchange) {
        return BindingBuilder
                .bind(defaultAlternateExchangeQueue)
                .to(defaultAlternateExchange);
    }
}
