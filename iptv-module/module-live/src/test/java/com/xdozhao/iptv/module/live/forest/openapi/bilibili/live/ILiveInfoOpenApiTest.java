package com.xdozhao.iptv.module.live.forest.openapi.bilibili.live;

import com.xdozhao.iptv.module.live.forest.response.bilibili.BiliBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@Slf4j
@SpringBootTest
class ILiveInfoOpenApiTest {

    @Autowired
    ILiveInfoOpenApi openApi;

    @Test
    void getInfo() {
        BiliBaseResponse result = openApi.getInfo(new BigDecimal(21623527));
        log.warn("Result:{}", result);
        Assertions.assertEquals(0, result.getCode());
    }

    @Test
    void roomInit() {
        BiliBaseResponse result = openApi.roomInit(new BigDecimal(21623527));
        log.warn("Result:{}", result);
        Assertions.assertEquals(0, result.getCode());
    }

    @Test
    void liveUserMasterInfo() {
        BiliBaseResponse result = openApi.liveUserMasterInfo(new BigDecimal(21623527));
        log.warn("Result:{}", result);
        Assertions.assertEquals(0, result.getCode());
    }

    @Test
    void getRoomPlayInfo() {
        BiliBaseResponse result = openApi.getRoomPlayInfo(new BigDecimal(21623527), 30000);
        log.warn("Result:{}", result);
        Assertions.assertEquals(0, result.getCode());
    }

    @Test
    void getAnchorInRoom() {
        BiliBaseResponse result = openApi.getAnchorInRoom(new BigDecimal(21623527));
        log.warn("Result:{}", result);
        Assertions.assertEquals(0, result.getCode());
    }
}