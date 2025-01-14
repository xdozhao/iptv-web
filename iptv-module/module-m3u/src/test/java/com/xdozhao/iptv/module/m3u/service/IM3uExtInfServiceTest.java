package com.xdozhao.iptv.module.m3u.service;

import com.xdozhao.iptv.module.m3u.entity.M3uExtInf;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class IM3uExtInfServiceTest {
    @Autowired
    private IM3uExtInfService iM3uExtInfService;

    @Test
    void select(){
        List<M3uExtInf> result = iM3uExtInfService.list();
        log.info("================================");
        log.info("Result: {}", result);
        log.info("================================");
    }
}