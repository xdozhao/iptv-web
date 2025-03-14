package com.xdozhao.iptv.module.live.configuration.rabbit.exchange;

import com.xdozhao.iptv.api.mq.rabbit.Constants;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 备份交换器是为了实现没有路由到队列的消息
 * <p>
 * 声明交换机的时候添加属性alternate-exchange，声明一个备用交换机，一般声明为fanout类型，这样交换机收到路由不到队列的消息就会发送到备用交换机绑定的队列中。
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/22 10:18
 */
@Configuration
public class AlternateExchangeConfig {

    /**
     * 默认备用交换机
     *
     * @return 默认备用交换机
     */
    @Bean
    FanoutExchange defaultAlternateExchange() {
        return ExchangeBuilder
                .fanoutExchange(Constants.Exchange.DEFAULT_ALTERNATE_EXCHANGE)
                .build();
    }
}
