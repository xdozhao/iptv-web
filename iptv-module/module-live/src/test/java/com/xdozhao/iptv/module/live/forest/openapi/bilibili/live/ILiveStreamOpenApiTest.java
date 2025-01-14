package com.xdozhao.iptv.module.live.forest.openapi.bilibili.live;

import com.xdozhao.iptv.module.live.forest.response.BiliBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ILiveStreamOpenApiTest {
    @Autowired
    ILiveStreamOpenApi openApi;

    @Test
    void playUrl() {
        BiliBaseResponse result = openApi.playUrl(21623527, "web", 30000);
        log.warn("Result:{}", result);
        Assertions.assertEquals(0, result.getCode());
    }
}