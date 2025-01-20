package com.xdozhao.iptv.module.live.task.bilibili;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.query.QueryWrapper;
import com.xdozhao.iptv.module.live.entity.LiveArea;
import com.xdozhao.iptv.module.live.entity.RoomInfo;
import com.xdozhao.iptv.module.live.entity.table.RoomInfoTableDef;
import com.xdozhao.iptv.module.live.forest.openapi.bilibili.live.ILiveAreaOpenApi;
import com.xdozhao.iptv.module.live.forest.openapi.bilibili.live.ILiveInfoOpenApi;
import com.xdozhao.iptv.module.live.forest.response.bilibili.BiliBaseResponse;
import com.xdozhao.iptv.module.live.service.ILiveAreaService;
import com.xdozhao.iptv.module.live.service.IRoomInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

import static com.xdozhao.iptv.module.live.entity.table.LiveAreaTableDef.LIVE_AREA;


/**
 * 直播间列表信息同步
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/15 15:26
 */
@Slf4j
@Component
@AllArgsConstructor
class RoomListTask {

    private ILiveAreaOpenApi liveAreaOpenApi;

    private ILiveInfoOpenApi liveInfoOpenApi;

    private ILiveAreaService liveAreaService;

    private IRoomInfoService roomInfoService;

    /**
     * 每30分钟执行一次
     */
    @Scheduled(cron = "0 0/30 * * * *")
    void run() {
        long startTime = System.currentTimeMillis();
        // 更新所有数据直播状态为 -1
        boolean updateAll = roomInfoService.update(new RoomInfo().setLiveStatus(-1), RoomInfoTableDef.ROOM_INFO.ID.isNotNull());
        log.info("更新所有数据直播状态为 -1: {}", updateAll);
        // 创建入库线程池
        ExecutorService executorService = initExecutorService();
        try {
            // 查询分区
            QueryWrapper liveAreaQueryWrapper = QueryWrapper.create()
                    .select(LIVE_AREA.ID)
                    .and(LIVE_AREA.PARENT_ID.isNull());
            List<LiveArea> liveAreaList = liveAreaService.list(liveAreaQueryWrapper);
            for (LiveArea liveArea : liveAreaList) {
                LinkedList<RoomInfo> roomInfoList = new LinkedList<>();
                int id = liveArea.getId();
                int total = 0;
                int curPage = 1;
                int pageSize = 90;
                // 分页查询 默认每个分区 查 10 * 90 条数据
                while ((curPage - 1) * pageSize <= total) {
                    // 获取分区直播间信息
                    BiliBaseResponse room = liveAreaOpenApi.getRoomList(id, curPage++, pageSize, "online");
                    if (room.getCode() != 0) {
                        break;
                    }
                    JSONObject roomDataInfo = JSON.parseObject(room.getData());
                    total = roomDataInfo.getInteger("count");
                    JSONArray roomArr = roomDataInfo.getJSONArray("list");
                    if (roomArr.isEmpty()) {
                        break;
                    }
                    // 添加当前页数据
                    for (int k = 0; k < roomArr.size(); k++) {
                        JSONObject areaRoomInfo = roomArr.getJSONObject(k);
                        roomInfoList.add(getRoomInfo(areaRoomInfo));
//                    Integer roomId = areaRoomInfo.getInteger("roomid");
//                    RoomInfo roomInfo = getRoomInfo(roomId);
//                    if (roomInfo == null) {
//                        break;
//                    }
//                    String uname = areaRoomInfo.getString("uname");
//                    String face = areaRoomInfo.getString("face");
//                    roomInfo.setUname(uname).setFace(face);
//                    roomInfoList.add(roomInfo);
                    }
                }
                // 数据入表
                for (RoomInfo item : roomInfoList) {
                    // 入库使用线程池
                    executorService.execute(() -> {
                        RoomInfo update = roomInfoService.getById(item.getId());
                        if (update == null) {
                            roomInfoService.save(item);
                        } else {
                            item.setUpdateTime(LocalDateTime.now());
                            item.setVersion(update.getVersion());
                            roomInfoService.updateById(item);
                        }
                    });
                }
                // 记录日志
                log.info("Room add from area id:{},change:{} total:{}", id, roomInfoList.size(), total);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
            // 监控线程池
            executorServiceMonitor(executorService);
            log.info("RoomListTask cost {}ms", System.currentTimeMillis() - startTime);
        }
    }

    /**
     * 获取直播间信息
     *
     * @param roomInfoBaseJson 从getRoomList返回的列表中取值，效率高
     *
     * @return
     */
    private RoomInfo getRoomInfo(JSONObject roomInfoBaseJson) {
        BigDecimal roomId = roomInfoBaseJson.getBigDecimal("roomid");
        BigDecimal uid = roomInfoBaseJson.getBigDecimal("uid");
        String title = roomInfoBaseJson.getString("title");
        String uname = roomInfoBaseJson.getString("uname");
        Integer online = roomInfoBaseJson.getInteger("online");
        String userCover = roomInfoBaseJson.getString("user_cover");
        String face = roomInfoBaseJson.getString("face");
        Integer parentId = roomInfoBaseJson.getInteger("parent_id");
        String parentName = roomInfoBaseJson.getString("parent_name");
        Integer areaId = roomInfoBaseJson.getInteger("area_id");
        String areaName = roomInfoBaseJson.getString("area_name");
        return new RoomInfo()
                .setRoomId(roomId)
                .setId(uid)
                .setTitle(title)
                .setUname(uname)
                .setOnline(online)
                .setUserCover(userCover)
                .setFace(face)
                .setParentAreaId(parentId)
                .setParentAreaName(parentName)
                .setAreaId(areaId)
                .setAreaName(areaName)
                .setLiveStatus(1)
                ;
    }

    /**
     * 创建线程池
     *
     * @return 线程池
     */
    private ExecutorService initExecutorService() {
        return new ThreadPoolExecutor(
                10,
                50,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    /**
     * 监控线程池
     *
     * @param executorService 线程池
     */
    private void executorServiceMonitor(ExecutorService executorService) {
        while (!executorService.isTerminated()) {
            log.debug("ExecutorServiceLocal monitor: {}", executorService);
            // 线程阻塞5秒
            LockSupport.parkNanos(5 * 1000 * 1000000L);
        }
        log.debug("ExecutorServiceLocal end: {}", executorService);
    }
}
