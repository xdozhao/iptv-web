package com.xdozhao.iptv.module.live.listener;


import com.alibaba.fastjson2.JSONObject;
import com.rabbitmq.client.Channel;
import com.xdozhao.iptv.api.mq.rabbit.Constants;
import com.xdozhao.iptv.common.mq.rabbit.annotation.AutoConfirmation;
import com.xdozhao.iptv.common.mq.rabbit.enitiy.Mail;
import com.xdozhao.iptv.module.live.entity.RoomInfo;
import com.xdozhao.iptv.module.live.mapper.RoomInfoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 14:43
 */
@Slf4j
@AllArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class RoomInfoInsertListener {
    private final RoomInfoMapper roomInfoMapper;

    @AutoConfirmation
    @RabbitListener(queues = Constants.Queue.DB_BRI_I, concurrency = "1-10")
    public void listenerInsertRoomInfoQueue(Channel channel, Message message, @Payload Mail<JSONObject> mail) {
        log.info("listener insert_room_info_queue:{},{}", mail.getId(), mail.getSendDate());
        JSONObject json = mail.getData();
        RoomInfo roomInfo = json.to(RoomInfo.class);
        RoomInfo update = roomInfoMapper.selectOneById(roomInfo.getId());
        if (update == null) {
            roomInfo.setCreateTime(LocalDateTime.now());
            roomInfoMapper.insert(roomInfo);
        } else {
            roomInfo.setUpdateTime(LocalDateTime.now());
            roomInfo.setVersion(update.getVersion());
            roomInfoMapper.update(roomInfo);
        }
    }

    @AutoConfirmation
    @RabbitListener(queues = Constants.Queue.DB_BRI_U)
    public void listenerUpdateRoomInfoQueue(Channel channel, Message message, @Payload Mail<JSONObject> mail) {
        log.info("listener update_room_info_queue:{},{}", mail.getId(), mail.getSendDate());
        JSONObject json = mail.getData();
        RoomInfo roomInfo = json.to(RoomInfo.class);
        int update = roomInfoMapper.update(roomInfo);
        log.debug("update roomInfo success:{}", update > 0);
    }
}
