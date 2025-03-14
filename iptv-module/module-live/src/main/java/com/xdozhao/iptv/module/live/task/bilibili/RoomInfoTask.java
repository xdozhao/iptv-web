package com.xdozhao.iptv.module.live.task.bilibili;

import cn.hutool.core.util.IdUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.xdozhao.iptv.api.mq.rabbit.Constants;
import com.xdozhao.iptv.common.mq.rabbit.enitiy.Mail;
import com.xdozhao.iptv.common.mq.rabbit.service.RabbitService;
import com.xdozhao.iptv.module.live.entity.RoomInfo;
import com.xdozhao.iptv.module.live.forest.openapi.bilibili.live.ILiveInfoOpenApi;
import com.xdozhao.iptv.module.live.service.IRoomInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static com.xdozhao.iptv.module.live.entity.table.RoomInfoTableDef.ROOM_INFO;

/**
 * 直播间信息获取
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/20 09:50
 */
@Slf4j
@Component
@AllArgsConstructor
class RoomInfoTask {
    private final RabbitService rabbitService;

    private final ILiveInfoOpenApi liveInfoOpenApi;

    private final IRoomInfoService roomInfoService;

    /**
     * 每天11点执行一次
     */
    @Scheduled(cron = "0 0 8 1/2 * *")
    void run() {
        long startTime = System.currentTimeMillis();
        long pageNum = 1;
        int pageSize = 1000;
        Page<RoomInfo> page;
        try {
            do {
                page = new Page<>(pageNum++, pageSize);
                QueryWrapper query = QueryWrapper.create()
                        .select(ROOM_INFO.ROOM_ID, ROOM_INFO.VERSION)
                        .orderBy(ROOM_INFO.ID.asc());
                page = roomInfoService.page(page, query);
                List<RoomInfo> records = page.getRecords();
                for (RoomInfo item : records) {
                    Mail<Serializable> mail = Mail.builder()
                            .id(IdUtil.simpleUUID())
                            .sendDate(LocalDateTime.now())
                            .data(item)
                            .build();
                    // 发送获取直播间信息并更新消息
                    rabbitService.convertAndSend(Constants.Exchange.D_LIVE_ROOM_INFO, Constants.Router.X_H_BL_L_RGI, mail);
                }
            } while (page.hasNext());
        } catch (Exception e) {
            log.error("Error:", e);
            throw e;
        } finally {
            log.info("RoomInfoTask cost {}ms", System.currentTimeMillis() - startTime);
        }
    }
}
