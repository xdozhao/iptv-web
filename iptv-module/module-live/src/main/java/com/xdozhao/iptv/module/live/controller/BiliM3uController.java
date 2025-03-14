package com.xdozhao.iptv.module.live.controller;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.text.StrFormatter;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.query.QueryWrapper;
import com.xdozhao.iptv.common.core.constant.M3u8Constant;
import com.xdozhao.iptv.common.m3u.util.M3uFileUtil;
import com.xdozhao.iptv.module.live.entity.RoomInfo;
import com.xdozhao.iptv.module.live.forest.openapi.bilibili.live.ILiveAreaOpenApi;
import com.xdozhao.iptv.module.live.forest.response.bilibili.BiliBaseResponse;
import com.xdozhao.iptv.module.live.service.IRoomInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static com.xdozhao.iptv.module.live.entity.table.RoomInfoTableDef.ROOM_INFO;

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

    private Environment env;

    private ILiveAreaOpenApi liveAreaOpenApi;

    private IRoomInfoService roomInfoService;

    @GetMapping("all")
    @Operation(description = "获取保存的直播间信息")
    public ResponseEntity<String> getAll(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder result = new StringBuilder(M3u8Constant.EXTM3U).append("\n");
        String liveBaseUrl = env.getProperty("bilibili.live.base-url");
        QueryWrapper query = new QueryWrapper();
        query.select(ROOM_INFO.ROOM_ID, ROOM_INFO.FACE, ROOM_INFO.UNAME, ROOM_INFO.TITLE, ROOM_INFO.PARENT_AREA_NAME, ROOM_INFO.AREA_NAME)
                .where(ROOM_INFO.LIVE_STATUS.eq(1).and(ROOM_INFO.ONLINE.ge(100)))
                .orderBy(ROOM_INFO.PARENT_AREA_ID.asc(), ROOM_INFO.AREA_ID.asc(), ROOM_INFO.ONLINE.desc());
        List<RoomInfo> roomInfoList = roomInfoService.list(query);
        for (RoomInfo roomInfo : roomInfoList) {
            BigDecimal roomid = roomInfo.getRoomId();
            String tvgLogo = roomInfo.getFace();
            String title = roomInfo.getUname() + "|" + roomInfo.getTitle();
            String groupTitle = roomInfo.getParentAreaName() + "|" + roomInfo.getAreaName();
            // #EXTINF:{} tvg-id="{}" tvg-name="{}" tvg-logo="{}" group-title="{}",{}
            //{}
            result.append(StrFormatter.format(M3u8Constant.EXTINF_EXT_TEMPLATE, "-1", "", "", tvgLogo, groupTitle, title, liveBaseUrl + roomid));
        }
        // 添加时间签名
        result.append(M3uFileUtil.getTimestampSign());
        String filename = "all.m3u";
        ContentDisposition contentDisposition = ContentDisposition.attachment().filename(FileNameUtil.getName(filename)).build();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .contentType(MediaType.valueOf(new Tika().detect(filename)))
                .body(result.toString());
    }

    @GetMapping("online")
    @Operation(description = "实时获取每个大分区Top900")
    public ResponseEntity<String> getOnlineRoom(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder result = new StringBuilder(M3u8Constant.EXTM3U).append("\n");
        String liveBaseUrl = env.getProperty("bilibili.live.base-url");
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
                    BigDecimal roomid = roomInfo.getBigDecimal("roomid");
                    String title = roomInfo.getString("uname") + "|" + roomInfo.getString("title");
                    String tvgLogo = roomInfo.getString("face");
                    String groupTitle = roomInfo.getString("parent_name") + "|" + roomInfo.getString("area_name");
                    // #EXTINF:{} tvg-id="{}" tvg-name="{}" tvg-logo="{}" group-title="{}",{}
                    //{}
                    result.append(StrFormatter.format(M3u8Constant.EXTINF_EXT_TEMPLATE, "-1", "", "", tvgLogo, groupTitle, title, liveBaseUrl + roomid));
                }
            }
        }
        // 添加时间签名
        result.append(M3uFileUtil.getTimestampSign());
        String filename = "online.m3u";
        ContentDisposition contentDisposition = ContentDisposition.attachment().filename(FileNameUtil.getName(filename)).build();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .contentType(MediaType.valueOf(new Tika().detect(filename)))
                .body(result.toString());
    }
}
