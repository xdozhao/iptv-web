package com.xdozhao.iptv.module.live.listener;

import cn.hutool.core.text.StrFormatter;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.rabbitmq.client.Channel;
import com.xdozhao.iptv.api.mq.rabbit.Constants;
import com.xdozhao.iptv.common.mq.rabbit.annotation.AutoConfirmation;
import com.xdozhao.iptv.common.mq.rabbit.enitiy.Mail;
import com.xdozhao.iptv.common.mq.rabbit.service.RabbitService;
import com.xdozhao.iptv.module.live.entity.RoomInfo;
import com.xdozhao.iptv.module.live.forest.openapi.bilibili.live.ILiveInfoOpenApi;
import com.xdozhao.iptv.module.live.forest.response.bilibili.BiliBaseResponse;
import com.xdozhao.iptv.module.live.mapper.RoomInfoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/22 17:59
 */
@Slf4j
@AllArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class LiveRoomInfoListener {
    private final RabbitService rabbitService;

    private final ILiveInfoOpenApi liveInfoOpenApi;

    private final RoomInfoMapper roomInfoMapper;

    @AutoConfirmation
    @RabbitListener(queues = Constants.Queue.BL_L_RGI)
    public void listenerUpdateRoomInfoQueue(Channel channel, Message message, @Payload Mail<JSONObject> mail) {
        log.info("listener update_room_info_queue:{},{}", mail.getId(), mail.getSendDate());
        JSONObject json = mail.getData();
        RoomInfo roomInfo = json.to(RoomInfo.class);
        BiliBaseResponse liveInfo = liveInfoOpenApi.getInfo(roomInfo.getRoomId());
        if (liveInfo.getCode() != 0) {
            String msg = StrFormatter.format("room getInfo error! {}", liveInfo);
            throw new IllegalArgumentException(msg);
        }
        JSONObject roomInfoJSON = JSON.parseObject(liveInfo.getData());
        // 发送更新直播间信息消息
        sendUpdateRoomInfo(roomInfoJSON, roomInfo.getVersion());
    }


    private boolean sendUpdateRoomInfo(JSONObject roomInfo, int version) {
        boolean result = false;
        try {
            // 消息数据
            RoomInfo updateRoomInfo = buildRoomInfo(roomInfo);
            // 设置更新时间和版本号
            updateRoomInfo
                    .setUpdateTime(LocalDateTime.now())
                    .setVersion(version);
            int update = roomInfoMapper.update(updateRoomInfo);
            result = update > 0;
        } catch (Exception e) {
            log.error("Error:", e);
        }
        return result;
    }

    RoomInfo buildRoomInfo(JSONObject roomInfo) {
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
        return new RoomInfo()
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
    }

}
