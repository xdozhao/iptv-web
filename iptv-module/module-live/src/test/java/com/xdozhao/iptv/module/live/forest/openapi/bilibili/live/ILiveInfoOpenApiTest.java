package com.xdozhao.iptv.module.live.forest.openapi.bilibili.live;

import com.xdozhao.iptv.module.live.forest.response.BiliBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ILiveInfoOpenApiTest {

    @Autowired
    ILiveInfoOpenApi openApi;

    @Test
    void getInfo() {
        BiliBaseResponse result = openApi.getInfo(21623527);
        log.warn("Result:{}", result);
        Assertions.assertEquals(0, result.getCode());
    }

    @Test
    void roomInit() {
        BiliBaseResponse result = openApi.roomInit(21623527);
        log.warn("Result:{}", result);
        Assertions.assertEquals(0, result.getCode());
    }

    @Test
    void liveUserMasterInfo() {
        BiliBaseResponse result = openApi.liveUserMasterInfo(433587902);
        log.warn("Result:{}", result);
        Assertions.assertEquals(0, result.getCode());
    }

    @Test
    void getRoomPlayInfo() {
        BiliBaseResponse result = openApi.getRoomPlayInfo(21623527, 30000);
        log.warn("Result:{}", result);
        Assertions.assertEquals(0, result.getCode());
    }

    @Test
    void getAnchorInRoom() {
        BiliBaseResponse result = openApi.getAnchorInRoom(21623527);
        log.warn("Result:{}", result);
        Assertions.assertEquals(0, result.getCode());
    }
}