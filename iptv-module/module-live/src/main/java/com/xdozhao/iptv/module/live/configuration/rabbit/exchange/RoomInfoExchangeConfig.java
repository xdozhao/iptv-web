package com.xdozhao.iptv.module.live.configuration.rabbit.exchange;

import com.xdozhao.iptv.api.mq.rabbit.Constants;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * b_room_info
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 14:15
 */
@Configuration
public class RoomInfoExchangeConfig {
    /**
     * b_room_info 数据交换机
     *
     * @return b_room_info 数据交换机
     */
    @Bean
    public DirectExchange directLiveRoomInfoExchange() {
        return ExchangeBuilder
                .directExchange(Constants.Exchange.D_LIVE_ROOM_INFO)
                .alternate(Constants.Exchange.DEFAULT_ALTERNATE_EXCHANGE)
                .build();
    }
}
