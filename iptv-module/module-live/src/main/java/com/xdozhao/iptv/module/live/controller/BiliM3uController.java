package com.xdozhao.iptv.module.live.controller;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.text.StrFormatter;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.xdozhao.iptv.common.core.constant.M3u8Constant;
import com.xdozhao.iptv.module.live.forest.openapi.bilibili.live.ILiveAreaOpenApi;
import com.xdozhao.iptv.module.live.forest.response.BiliBaseResponse;
import com.xdozhao.iptv.module.live.service.IRoomInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * B站直播 M3U文件
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/13 17:39
 */
@RestController
@AllArgsConstructor
@Tag(name = "B站直播 M3U文件")
@RequestMapping("/m3u")
public class BiliM3uController {
    ILiveAreaOpenApi liveAreaOpenApi;

    private IRoomInfoService roomInfoService;

    /**
     * 每个大分区Top200
     *
     * @param request
     * @param response
     *
     * @return
     */
    @GetMapping("all")
    @Operation(description = "每个大分区Top200")
    public ResponseEntity<String> getUrlByRoomeId(HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
        StringBuilder result = new StringBuilder(M3u8Constant.EXTM3U).append("\n");
        // 获取全部直播间分区列表
        BiliBaseResponse area = liveAreaOpenApi.getList();
        if (area.getCode() != 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        // 分区列表
        JSONArray areaArr = JSON.parseArray(area.getData());
        for (int i = 0; i < areaArr.size(); i++) {
            JSONObject areaInfo = areaArr.getJSONObject(i);
            int id = areaInfo.getInteger("id");
            int pageSize = 90;
            // 分页查询 默认每个分区 查 10 * 90 条数据
            for (int j = 1; j <= 10; j++) {
                // 获取分区直播间信息
                BiliBaseResponse room = liveAreaOpenApi.getRoomList(id, j, pageSize, "online");
                if (room.getCode() != 0) {
                    break;
                }
                JSONObject roomDataInfo = JSON.parseObject(room.getData());
                JSONArray roomArr = roomDataInfo.getJSONArray("list");
                if (roomArr.isEmpty()) {
                    break;
                }
                // 添加当前页数据
                for (int k = 0; k < roomArr.size(); k++) {
                    JSONObject roomInfo = roomArr.getJSONObject(k);
                    Integer roomid = roomInfo.getInteger("roomid");
                    String title = roomInfo.getString("uname") + "|" + roomInfo.getString("title");
                    String tvgLogo = roomInfo.getString("face");
                    String groupTitle = roomInfo.getString("parent_name") + "|" + roomInfo.getString("area_name");
                    // #EXTINF:{} tvg-id="{}" tvg-name="{}" tvg-logo="{}" group-title="{}",{}
                    //{}
                    result.append(StrFormatter.format(M3u8Constant.EXTINF_EXT_TEMPLATE, "-1", "", "", tvgLogo, groupTitle, title, "http://127.0.0.1:8911/live/bilibili/" + roomid));
                }
            }
        }
        // 添加时间戳
        LocalDateTime now = LocalDateTime.now();
        String dateTimeStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        result.append(StrFormatter.format(M3u8Constant.EXTINF_EXT_TEMPLATE, "-1", "", "", "", "提示", "更新时间 " + dateTimeStr, "https://github.com/xdozhao/iptv-web"));
        String filename = "all.m3u";
        ContentDisposition contentDisposition = ContentDisposition.attachment().filename(FileNameUtil.getName(filename)).build();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .contentType(MediaType.valueOf(new Tika().detect(filename)))
                .body(result.toString());
    }
}
