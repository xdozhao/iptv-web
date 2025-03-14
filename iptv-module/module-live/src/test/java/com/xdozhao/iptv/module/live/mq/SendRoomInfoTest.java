package com.xdozhao.iptv.module.live.mq;


import cn.hutool.core.util.IdUtil;
import com.xdozhao.iptv.api.mq.rabbit.Constants;
import com.xdozhao.iptv.common.mq.rabbit.enitiy.Mail;
import com.xdozhao.iptv.common.mq.rabbit.service.RabbitService;
import com.xdozhao.iptv.module.live.entity.RoomInfo;
import com.xdozhao.iptv.module.live.service.IRoomInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 15:02
 */
@Slf4j
@SpringBootTest
class SendRoomInfoTest {
    @Autowired
    private IRoomInfoService roomInfoService;
    @Autowired
    RabbitService rabbitService;

    @Test
    void send() {
        RoomInfo roomInfo = roomInfoService.getById("2864");
        Mail<Serializable> mail = Mail.builder()
                .id(IdUtil.simpleUUID())
                .sendDate(LocalDateTime.now())
                .data(roomInfo)
                .build();
        rabbitService.convertAndSend(Constants.Exchange.D_LIVE_ROOM_INFO, Constants.Router.X_H_BL_L_RGI, mail);
    }
}
