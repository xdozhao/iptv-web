package com.xdozhao.iptv.common.mq.rabbit.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 09:49
 */
@Slf4j
@EnableRabbit
@Configuration
@AllArgsConstructor
public class RabbitConfigure {

    private final RabbitTemplate.ConfirmCallback confirmCallback;

    private final RabbitTemplate.ReturnsCallback returnsCallback;

    /**
     * 配置并返回一个 RabbitTemplate 实例以发送和接收消息。
     *
     * @param connectionFactory 用于与 RabbitMQ 建立连接的连接工厂。
     *
     * @return 已配置的 RabbitTemplate 实例。
     */
    @Bean
    RabbitTemplate rabbitTemplateService(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //默认是用jdk序列化
        //数据转换为json存入消息队列，方便可视化界面查看消息数据
        rabbitTemplate.setMessageConverter(messageConverter());
        //设置开启Manatory，才能触发回调函数，无论消息推送结果怎么样都会强制调用回调函数
        rabbitTemplate.setMandatory(true);
        // 设置确认发送到交换机的回调函数
        rabbitTemplate.setConfirmCallback(confirmCallback);
        // 设置确认消息发送失败被退回的回调
        rabbitTemplate.setReturnsCallback(returnsCallback);
        return rabbitTemplate;
    }

    @Bean
    FastjsonMessageConverter messageConverter() {
        return new FastjsonMessageConverter();
    }
}
