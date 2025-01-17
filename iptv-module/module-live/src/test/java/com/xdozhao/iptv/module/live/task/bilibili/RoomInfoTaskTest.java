package com.xdozhao.iptv.module.live.task.bilibili;

import com.xdozhao.iptv.module.live.service.ILiveAreaService;
import com.xdozhao.iptv.module.live.service.IRoomInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class RoomInfoTaskTest {
    @Autowired
    RoomInfoTask roomInfoTask;

    @Test
    void run() {
        roomInfoTask.run();
    }

    @Autowired
    private ILiveAreaService liveAreaService;
    @Autowired
    private IRoomInfoService roomInfoService;

    @Test
    void list() {
        roomInfoService.getById(0);
    }
}