package com.xdozhao.iptv.module.live.configuration.rabbit.queue;

import com.xdozhao.iptv.api.mq.rabbit.Constants;
import com.xdozhao.iptv.common.core.constant.TimeMsConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * b_room_info
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 14:14
 */
@Configuration
public class RoomInfoQueueConfig {
    /**
     * b_room_info 插入数据队列
     *
     * @return b_room_info 插入数据队列
     */
    @Bean
    public Queue insertRoomInfoQueue() {
        return QueueBuilder
                .durable(Constants.Queue.DB_BRI_I)
                .deadLetterExchange(Constants.Exchange.DEFAULT_DEAD_LETTER)
                .deadLetterRoutingKey(Constants.Router.DEAD_LETTER_ROUTER)
                .ttl(10 * TimeMsConstants.ONE_MINUTE)
                .build();
    }

    /**
     * b_room_info 更新数据队列
     *
     * @return b_room_info 更新数据队列
     */
    @Bean
    public Queue updateRoomInfoQueue() {
        return QueueBuilder
                .durable(Constants.Queue.DB_BRI_U)
                .deadLetterExchange(Constants.Exchange.DEFAULT_DEAD_LETTER)
                .deadLetterRoutingKey(Constants.Router.DEAD_LETTER_ROUTER)
                .ttl(10 * TimeMsConstants.ONE_MINUTE)
                .build();
    }

    /**
     * 获取直播间信息队列
     *
     * @return 获取直播间信息队列
     */
    @Bean
    public Queue liveRoomInfoQueue() {
        return QueueBuilder
                .durable(Constants.Queue.BL_L_RGI)
                .deadLetterExchange(Constants.Exchange.DEFAULT_DEAD_LETTER)
                .deadLetterRoutingKey(Constants.Router.DEAD_LETTER_ROUTER)
                .ttl(3 * TimeMsConstants.ONE_HOUR)
                .build();
    }

}
