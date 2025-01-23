package com.xdozhao.iptv.module.live.listener;

import com.alibaba.fastjson2.JSONObject;
import com.rabbitmq.client.Channel;
import com.xdozhao.iptv.api.mq.rabbit.Constants;
import com.xdozhao.iptv.common.mq.rabbit.annotation.AutoConfirmation;
import com.xdozhao.iptv.common.mq.rabbit.enitiy.Mail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/23 10:49
 */
@Slf4j
@AllArgsConstructor
@Service
public class AlternateExchangeListener {

    /**
     * 监听 默认备用交换机队列
     *
     * @param channel
     * @param message
     * @param mail
     */
    @AutoConfirmation
    @RabbitListener(queues = Constants.Queue.DEFAULT_ALTERNATE_EXCHANGE_QUEUE)
    public void defaultAlternateExchangeQueue(Channel channel, Message message, @Payload Mail<JSONObject> mail) {
        log.error("listener default_alternate_exchange_queue:{},{},{}", mail.getId(), mail.getSendDate(), mail.getSendDate());
    }
}
