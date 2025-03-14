package com.xdozhao.iptv.module.live.configuration.rabbit.bind;

import com.xdozhao.iptv.api.mq.rabbit.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * b_room_info
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 14:16
 */
@Configuration
public class RoomInfoExchangeQueueBindConfig {
    /**
     * b_room_info 插入数据队列、交换机绑定
     *
     * @param insertRoomInfoQueue        b_room_info 插入数据队列
     * @param directLiveRoomInfoExchange room_info 数据交换机
     *
     * @return 绑定
     */
    @Bean
    public Binding insertRoomInfoRouterBinding(Queue insertRoomInfoQueue, DirectExchange directLiveRoomInfoExchange) {
        return BindingBuilder
                .bind(insertRoomInfoQueue)
                .to(directLiveRoomInfoExchange)
                .with(Constants.Router.X_DB_BRI_I);  // 定义路由规则
    }


    /**
     * b_room_info 插入数据队列、交换机绑定
     *
     * @param updateRoomInfoQueue        b_room_info 更新数据队列
     * @param directLiveRoomInfoExchange room_info 数据交换机
     *
     * @return 绑定
     */
    @Bean
    public Binding updateRoomInfoRouterBinding(Queue updateRoomInfoQueue, DirectExchange directLiveRoomInfoExchange) {
        return BindingBuilder
                .bind(updateRoomInfoQueue)
                .to(directLiveRoomInfoExchange)
                .with(Constants.Router.X_DB_RBI_U);  // 定义路由规则
    }


    /**
     * 获取直播间信息队列、交换机绑定
     *
     * @param liveRoomInfoQueue          获取直播间信息队列
     * @param directLiveRoomInfoExchange room_info 数据交换机
     *
     * @return 绑定
     */
    @Bean
    public Binding liveRoomInfoRouterBinding(Queue liveRoomInfoQueue, DirectExchange directLiveRoomInfoExchange) {
        return BindingBuilder
                .bind(liveRoomInfoQueue)
                .to(directLiveRoomInfoExchange)
                .with(Constants.Router.X_H_BL_L_RGI);  // 定义路由规则
    }
}
