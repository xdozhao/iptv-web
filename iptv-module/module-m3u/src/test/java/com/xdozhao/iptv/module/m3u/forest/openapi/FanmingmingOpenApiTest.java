package com.xdozhao.iptv.module.m3u.forest.openapi;

import com.dtflys.forest.http.ForestResponse;
import com.dtflys.forest.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class FanmingmingOpenApiTest {
    @Autowired
    FanmingmingOpenApi openApi;
    @Test
    void ipv6() throws Exception {
        ForestResponse<String> rsp = openApi.ipv6();
        Assertions.assertEquals(HttpStatus.OK,rsp.getStatusCode());
    }
}