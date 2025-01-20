package com.xdozhao.iptv.module.live.task.bilibili;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.xdozhao.iptv.module.live.entity.RoomInfo;
import com.xdozhao.iptv.module.live.entity.table.RoomInfoTableDef;
import com.xdozhao.iptv.module.live.forest.openapi.bilibili.live.ILiveInfoOpenApi;
import com.xdozhao.iptv.module.live.forest.response.bilibili.BiliBaseResponse;
import com.xdozhao.iptv.module.live.service.IRoomInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    private ILiveInfoOpenApi liveInfoOpenApi;

    private IRoomInfoService roomInfoService;

    /**
     * 每天11点执行一次
     */
    @Scheduled(cron = "0 0 8 * * *")
    void run() {
        long startTime = System.currentTimeMillis();
        long pageNum = 1;
        int pageSize = 200;
        Page<RoomInfo> page;
        try {
            do {
                page = new Page<>(pageNum++, pageSize);
                QueryWrapper query = QueryWrapper.create()
                        .select(RoomInfoTableDef.ROOM_INFO.ROOM_ID, RoomInfoTableDef.ROOM_INFO.VERSION)
                        .orderBy(RoomInfoTableDef.ROOM_INFO.ID.asc());
                page = roomInfoService.page(page, query);
                List<RoomInfo> records = page.getRecords();
                for (RoomInfo item : records) {
                    BiliBaseResponse liveInfo = liveInfoOpenApi.getInfo(item.getRoomId());
                    if (liveInfo.getCode() != 0) {
                        continue;
                    }
                    JSONObject roomInfo = JSON.parseObject(liveInfo.getData());
                    boolean updated = updateRoomInfo(roomInfo, item.getVersion());
                    log.debug("updateRoomInfo:{},{}", item.getRoomId(), updated);
                }
            } while (page.hasNext());
        } catch (Exception e) {
            log.error("Error:", e);
            throw e;
        } finally {
            log.info("RoomInfoTask cost {}ms", System.currentTimeMillis() - startTime);
        }
    }

    private boolean updateRoomInfo(JSONObject roomInfo, int version) {
        boolean result = false;
        try {
            BigDecimal uid = roomInfo.getBigDecimal("uid");
            BigDecimal roomId = roomInfo.getBigDecimal("room_id");
            Integer shortId = roomInfo.getInteger("short_id");
            Integer attention = roomInfo.getInteger("attention");
            Integer online = roomInfo.getInteger("online");
            Boolean isPortrait = roomInfo.getBoolean("is_portrait");
            String description = roomInfo.getString("description");
            Integer liveStatus = roomInfo.getInteger("live_status");
            Integer areaId = roomInfo.getInteger("area_id");
            Integer parentAreaId = roomInfo.getInteger("parent_area_id");
            String parentAreaName = roomInfo.getString("parent_area_name");
            Integer oldAreaId = roomInfo.getInteger("old_area_id");
            String background = roomInfo.getString("background");
            String title = roomInfo.getString("title");
            String userCover = roomInfo.getString("user_cover");
            String keyframe = roomInfo.getString("keyframe");
            Boolean isStrictRooms = roomInfo.getBoolean("is_strict_room");
            String liveTime = roomInfo.getString("live_time");
            String tags = roomInfo.getString("tags");
            Integer isAnchor = roomInfo.getInteger("is_anchor");
            String roomSilentType = roomInfo.getString("room_silent_type");
            Integer roomSilentLevel = roomInfo.getInteger("room_silent_level");
            Integer roomSilentSecond = roomInfo.getInteger("room_silent_second");
            String areaName = roomInfo.getString("area_name");
            String pardants = roomInfo.getString("pardants");
            String areaPardants = roomInfo.getString("area_pardants");
            Integer hotWordsStatus = roomInfo.getInteger("hot_words_status");
            String verify = roomInfo.getString("verify");
            String upSession = roomInfo.getString("up_session");
            Integer pkStatus = roomInfo.getInteger("pk_status");
            Integer pkId = roomInfo.getInteger("pk_id");
            Integer battleId = roomInfo.getInteger("battle_id");
            Integer allowChangeAreaTime = roomInfo.getInteger("allow_change_area_time");
            Integer allowUploadCoverTime = roomInfo.getInteger("allow_upload_cover_time");
            RoomInfo updateRoomInfo = new RoomInfo()
                    .setId(uid)
                    .setRoomId(roomId)
                    .setShortId(shortId)
                    .setAttention(attention)
                    .setOnline(online)
                    .setIsPortrait(isPortrait)
                    .setDescription(description)
                    .setLiveStatus(liveStatus)
                    .setAreaId(areaId)
                    .setAreaName(areaName)
                    .setParentAreaId(parentAreaId)
                    .setParentAreaName(parentAreaName)
                    .setOldAreaId(oldAreaId)
                    .setBackground(background)
                    .setTitle(title)
                    .setUserCover(userCover)
                    .setKeyframe(keyframe)
                    .setIsStrictRoom(isStrictRooms)
                    .setLiveTime(liveTime)
                    .setTags(tags)
                    .setIsAnchor(isAnchor)
                    .setRoomSilentType(roomSilentType)
                    .setRoomSilentLevel(roomSilentLevel)
                    .setRoomSilentSecond(roomSilentSecond)
                    .setPardants(pardants)
                    .setAreaPardants(areaPardants)
                    .setHotWordsStatus(hotWordsStatus)
                    .setVerify(verify)
                    .setUpSession(upSession)
                    .setPkStatus(pkStatus)
                    .setPkId(pkId)
                    .setBattleId(battleId)
                    .setAllowChangeAreaTime(allowChangeAreaTime)
                    .setAllowUploadCoverTime(allowUploadCoverTime);
            updateRoomInfo
                    .setUpdateTime(LocalDateTime.now())
                    .setVersion(version);
            result = roomInfoService.updateById(updateRoomInfo);
        } catch (Exception e) {
            log.error("Error:", e);
        }
        return result;
    }
}
