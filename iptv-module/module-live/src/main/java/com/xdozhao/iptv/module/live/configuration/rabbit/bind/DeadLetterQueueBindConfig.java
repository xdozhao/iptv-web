package com.xdozhao.iptv.module.live.configuration.rabbit.bind;

import com.xdozhao.iptv.api.mq.rabbit.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 14:26
 */
@Configuration
public class DeadLetterQueueBindConfig {
    /**
     * 绑定死信队列、死信交换机、路由规则
     *
     * @param defaultDeadLetterQueue    默认死信队列
     * @param defaultDeadLetterExchange 默认死信交换机
     *
     * @return
     */
    @Bean
    public Binding deadLetterQueueBing(Queue defaultDeadLetterQueue, DirectExchange defaultDeadLetterExchange) {
        return BindingBuilder
                .bind(defaultDeadLetterQueue)
                .to(defaultDeadLetterExchange)
                .with(Constants.Router.DEAD_LETTER_ROUTER);
    }
}
