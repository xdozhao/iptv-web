package com.xdozhao.iptv.module.live.configuration.rabbit.queue;

import com.xdozhao.iptv.api.mq.rabbit.Constants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 死信队列是一个专门用于接收无法被消费者正确处理的消息的队列。
 * <p>
 * 当消息被投递到死信队列时，可以进行一些特殊的处理操作，比如记录日志、发送告警等。
 * <p>
 * 同时，死信队列也可以配置一些特定的参数，比如过期时间、最大长度等，用于控制消息的生命周期。
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 14:13
 */
@Configuration
public class DeadLetterQueueConfig {
    /**
     * 默认死信队列
     *
     * @return queue
     */
    @Bean
    public Queue defaultDeadLetterQueue() {
        return QueueBuilder.durable(Constants.Queue.DEFAULT_DEAD_LETTER_QUEUE).build();
    }
}
