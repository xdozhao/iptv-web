package com.xdozhao.iptv.module.live.task.bilibili;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class LiveAreaTaskTest {

    @Autowired
    LiveAreaTask liveAreaTask;

    @Test
    void run() {
        liveAreaTask.run();
    }
}