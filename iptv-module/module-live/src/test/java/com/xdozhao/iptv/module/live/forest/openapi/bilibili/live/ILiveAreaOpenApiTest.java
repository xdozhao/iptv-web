package com.xdozhao.iptv.module.live.forest.openapi.bilibili.live;

import com.xdozhao.iptv.module.live.forest.response.BiliBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ILiveAreaOpenApiTest {
    @Autowired
    ILiveAreaOpenApi openApi;

    @Test
    void getList() {
        BiliBaseResponse result = openApi.getList();
        log.warn("Result:{}", result);
        Assertions.assertEquals(0, result.getCode());
    }

    @Test
    void getRoomList() {
        BiliBaseResponse result = openApi.getRoomList(11, 1, 90, "online");
        log.warn("Result:{}", result);
        Assertions.assertEquals(0, result.getCode());
    }
}