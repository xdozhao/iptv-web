package com.xdozhao.iptv.module.live.forest.openapi.bilibili.live;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;
import com.xdozhao.iptv.module.live.forest.response.BiliBaseResponse;

import java.math.BigDecimal;

/**
 * 直播间信息
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/10 16:14
 */
@BaseRequest(baseURL = "https://api.live.bilibili.com")
public interface ILiveInfoOpenApi {
    /**
     * 获取直播间信息
     *
     * @param roomId 目标直播间号（短号）
     *
     * @return 根对象
     */
    @Get(url = "/room/v1/Room/get_info")
    BiliBaseResponse getInfo(@Query("room_id") BigDecimal roomId);

    /**
     * 获取直播间信息
     *
     * @param id 目标直播间号（短号）
     *
     * @return 根对象
     */
    @Get(url = "/room/v1/Room/room_init")
    BiliBaseResponse roomInit(@Query("id") BigDecimal id);

    /**
     * 获取主播信息
     *
     * @param uid 目标用户mid
     *
     * @return 根对象
     */
    @Get(url = "/live_user/v1/Master/info")
    BiliBaseResponse liveUserMasterInfo(@Query("uid") BigDecimal uid);

    /**
     * 获取直播间信息
     *
     * @param roomId 直播间id
     * @param qn     清晰度编码
     *
     * @return 根对象
     */
    @Get(url = "/xlive/web-room/v2/index/getRoomPlayInfo?room_id={0}&protocol=0,1&format=0,1,2&codec=0,1,2&qn={1}&platform=web&ptype=8&dolby=5&panorama=1&hdr_type=0,1")
    BiliBaseResponse getRoomPlayInfo(BigDecimal roomId, int qn);


    /**
     * 获取直播间主播信息
     *
     * @param roomId 目标直播间号（短号）
     *
     * @return 根对象
     */
    @Get(url = "/live_user/v1/UserInfo/get_anchor_in_room")
    BiliBaseResponse getAnchorInRoom(@Query("roomid") BigDecimal roomId);
}
