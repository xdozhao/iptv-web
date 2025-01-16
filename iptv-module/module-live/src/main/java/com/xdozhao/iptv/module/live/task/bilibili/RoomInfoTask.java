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
import com.xdozhao.iptv.module.live.forest.response.BiliBaseResponse;
import com.xdozhao.iptv.module.live.service.ILiveAreaService;
import com.xdozhao.iptv.module.live.service.IRoomInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static com.xdozhao.iptv.module.live.entity.table.LiveAreaTableDef.LIVE_AREA;


/**
 * 直播间信息同步
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/15 15:26
 */
@Slf4j
@Component
@AllArgsConstructor
class RoomInfoTask {

    private ILiveAreaOpenApi liveAreaOpenApi;

    private ILiveInfoOpenApi liveInfoOpenApi;

    private ILiveAreaService liveAreaService;

    private IRoomInfoService roomInfoService;

    void run() {
        // 更新所有数据直播状态为 -1
        boolean updateAll = roomInfoService.update(RoomInfo.create().setLiveStatus(-1), RoomInfoTableDef.ROOM_INFO.ID.isNull());
        log.info("更新所有数据直播状态为 -1: {}", updateAll);
        // 查询分区
        QueryWrapper liveAreaQueryWrapper = new QueryWrapper()
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
                RoomInfo update = roomInfoService.getById(item.getId());
                if (update == null) {
                    roomInfoService.save(item);
                } else {
                    item.setUpdateTime(LocalDateTime.now());
                    item.setVersion(update.getVersion());
                    roomInfoService.updateById(item);
                }
            }
            // 记录日志
            log.info("Room add from area id:{},change:{} total:{}", id, roomInfoList.size(), total);
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
        return RoomInfo.create()
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
     * 获取直播间信息
     * <p>
     * 从getRoomList返回的列表中取值，效率低，数据较全
     *
     * @param roomId 直播间id
     *
     * @return
     */
    private RoomInfo getRoomInfo(BigDecimal roomId) {
        // 调接口获取直播间信息
        BiliBaseResponse liveInfo = liveInfoOpenApi.getInfo(roomId);
        if (liveInfo.getCode() != 0) {
            return null;
        }
        JSONObject roomInfo = JSON.parseObject(liveInfo.getData());
        BigDecimal uid = roomInfo.getBigDecimal("uid");
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
        return RoomInfo.create()
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
                .setAllowUploadCoverTime(allowUploadCoverTime)
                ;
    }
}
