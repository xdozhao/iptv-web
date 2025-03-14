package com.xdozhao.iptv.module.live.configuration.rabbit.exchange;

import com.xdozhao.iptv.api.mq.rabbit.Constants;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 14:25
 */
@Configuration
public class DeadLetterExchangeConfig {
    /**
     * 默认死信交换机
     *
     * @return
     */
    @Bean
    public DirectExchange defaultDeadLetterExchange() {
        return ExchangeBuilder.directExchange(Constants.Exchange.DEFAULT_DEAD_LETTER).build();
    }
}
