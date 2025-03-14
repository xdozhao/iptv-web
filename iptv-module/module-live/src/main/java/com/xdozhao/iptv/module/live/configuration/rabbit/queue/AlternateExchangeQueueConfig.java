package com.xdozhao.iptv.module.live.configuration.rabbit.queue;

import com.xdozhao.iptv.api.mq.rabbit.Constants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/22 10:17
 */
@Configuration
public class AlternateExchangeQueueConfig {
    /**
     * 默认备用交换机队列
     *
     * @return 默认备用交换机队列
     */
    @Bean
    Queue defaultAlternateExchangeQueue() {
        return QueueBuilder.durable(Constants.Queue.DEFAULT_ALTERNATE_EXCHANGE_QUEUE).build();
    }
}
