package com.xdozhao.iptv.module.live.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.xdozhao.iptv.module.live.forest.openapi.bilibili.live.ILiveInfoOpenApi;
import com.xdozhao.iptv.module.live.forest.openapi.bilibili.live.ILiveStreamOpenApi;
import com.xdozhao.iptv.module.live.forest.response.bilibili.BiliBaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * bilibili直播链接
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/13 10:36
 */
@RestController
@AllArgsConstructor
@Tag(name = "bilibili直播链接")
@RequestMapping("/bilibili")
public class BiliController {
    ILiveInfoOpenApi liveInfoOpenApi;
    ILiveStreamOpenApi liveStreamOpenApi;

    /**
     * 查询直播链接通过直播间Id
     *
     * @param request
     * @param response
     *
     * @return
     */
    @GetMapping("{id}")
    @Operation(description = "查询直播链接通过直播间Id")
    public ResponseEntity<String> getUrlByRoomeId(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
        String result = null;
        // 获取直播间信息
        BiliBaseResponse info = liveInfoOpenApi.getInfo(new BigDecimal(id));
        if (info.getCode() != 0 && info.getData() != null) {
            // 直播间不存在
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .location(new URI("https://github.com/xdozhao/iptv-web/404/liveInfo"))
                    .build();
        }
        JSONObject infoData = JSON.parseObject(info.getData());
        // 直播状态
        Integer liveStatus = infoData.getInteger("live_status");
        if (liveStatus != 1) {
            // 未开播
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .location(new URI("https://github.com/xdozhao/iptv-web/404/liveStatus_" + liveStatus))
                    .build();
        }
        // 直播间长号
        BigDecimal roomId = infoData.getBigDecimal("room_id");
        // 根据真实直播间号获取直播视频流
        BiliBaseResponse playUrl = liveStreamOpenApi.playUrl(roomId, "h5", 10000);
        if (playUrl.getCode() != 0 && playUrl.getData() != null) {
            // 获取直播链接失败
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .location(new URI("https://github.com/xdozhao/iptv-web/404/no_playUrl"))
                    .build();
        }
        JSONObject data = JSON.parseObject(playUrl.getData());
        // 直播流url组
        assert data != null;
        JSONArray durlArr = data.getJSONArray("durl");
        for (int i = 0; i < durlArr.size(); i++) {
            // 服务器
            JSONObject durl = durlArr.getJSONObject(i);
            if (StringUtils.isNotBlank(durl.getString("url"))) {
                result = durl.getString("url");
                break;
            }
        }
        if (StringUtils.isBlank(result)) {
            // 获取直播链接失败
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .location(new URI("https://github.com/xdozhao/iptv-web/404/no_url"))
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .location(new URI(result))
                .build();
    }

    /**
     * 查询直播链接通过UID
     *
     * @param request
     * @param response
     *
     * @return
     */
    @GetMapping("u/{id}")
    @Operation(description = "查询直播链接通过UID")
    public ResponseEntity<String> getUrlByUid(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
        String result = null;
        BiliBaseResponse userMasterInfo = liveInfoOpenApi.liveUserMasterInfo(new BigDecimal(id));
        if (userMasterInfo.getCode() != 0 && userMasterInfo.getData() != null) {
            // 主播信息不存在
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .location(new URI("https://github.com/xdozhao/iptv-web/404/userMasterInfo"))
                    .build();
        }
        JSONObject userMasterInfoData = JSON.parseObject(userMasterInfo.getData());
        BigDecimal shotRoomId = userMasterInfoData.getBigDecimal("room_id");
        // 获取直播间信息
        BiliBaseResponse info = liveInfoOpenApi.getInfo(shotRoomId);
        if (info.getCode() != 0 && info.getData() != null) {
            // 直播间不存在
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .location(new URI("https://github.com/xdozhao/iptv-web/404/liveInfo"))
                    .build();
        }
        JSONObject infoData = JSON.parseObject(info.getData());
        // 直播状态
        Integer liveStatus = infoData.getInteger("live_status");
        if (liveStatus != 1) {
            // 未开播
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .location(new URI("https://github.com/xdozhao/iptv-web/404/liveStatus_" + liveStatus))
                    .build();
        }
        // 直播间长号
        BigDecimal roomId = infoData.getBigDecimal("room_id");
        // 根据真实直播间号获取直播视频流
        BiliBaseResponse playUrl = liveStreamOpenApi.playUrl(roomId, "h5", 10000);
        if (playUrl.getCode() != 0 && playUrl.getData() != null) {
            // 获取直播链接失败
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .location(new URI("https://github.com/xdozhao/iptv-web/404/no_playUrl"))
                    .build();
        }
        JSONObject data = JSON.parseObject(playUrl.getData());
        // 直播流url组
        JSONArray durlArr = data.getJSONArray("durl");
        for (int i = 0; i < durlArr.size(); i++) {
            // 服务器
            JSONObject durl = durlArr.getJSONObject(i);
            if (StringUtils.isNotBlank(durl.getString("url"))) {
                result = durl.getString("url");
                break;
            }
        }
        if (StringUtils.isBlank(result)) {
            // 获取直播链接失败
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .location(new URI("https://github.com/xdozhao/iptv-web/404/no_url"))
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .location(new URI(result))
                .build();
    }
}
