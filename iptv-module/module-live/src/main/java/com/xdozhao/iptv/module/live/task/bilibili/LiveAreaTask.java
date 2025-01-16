package com.xdozhao.iptv.module.live.task.bilibili;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.xdozhao.iptv.module.live.entity.LiveArea;
import com.xdozhao.iptv.module.live.forest.openapi.bilibili.live.ILiveAreaOpenApi;
import com.xdozhao.iptv.module.live.forest.openapi.bilibili.live.ILiveInfoOpenApi;
import com.xdozhao.iptv.module.live.forest.response.BiliBaseResponse;
import com.xdozhao.iptv.module.live.service.ILiveAreaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * 直播分区信息同步
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/15 15:25
 */
@Slf4j
@Component
@AllArgsConstructor
class LiveAreaTask {

    private ILiveAreaOpenApi liveAreaOpenApi;

    private ILiveInfoOpenApi liveInfoOpenApi;

    private ILiveAreaService liveAreaService;

    void run() {
        // 获取全部直播间分区列表
        BiliBaseResponse area = liveAreaOpenApi.getList();
        if (area.getCode() != 0) {
            log.error("liveAreaOpenApi.getList code:{},msg:{}", area.getCode(), area.getMsg());
            return;
        }
        // 分区列表
        JSONArray areaArr = JSON.parseArray(area.getData());
        for (int i = 0; i < areaArr.size(); i++) {
            List<LiveArea> areaList = new LinkedList<>();
            JSONObject parentArea = areaArr.getJSONObject(i);
            // 父分区列表
            int pId = parentArea.getInteger("id");
            String pName = parentArea.getString("name");
            areaList.add(
                    LiveArea.create()
                            .setId(pId)
                            .setName(pName)
                            .setUpdateTime(LocalDateTime.now())
            );
            JSONArray childAreaList = parentArea.getJSONArray("list");
            for (int j = 0; j < childAreaList.size(); j++) {
                JSONObject childArea = childAreaList.getJSONObject(j);
                // 父分区列表
                Integer id = childArea.getInteger("id");
                Integer parentId = childArea.getInteger("parent_id");
                String parentName = childArea.getString("parent_name");
                Integer oldAreaId = childArea.getInteger("old_area_id");
                String name = childArea.getString("name");
                Integer actId = childArea.getInteger("act_id");
                Integer pkStatus = childArea.getInteger("pk_status");
                Integer hotStatus = childArea.getInteger("hot_status");
                Integer lockStatus = childArea.getInteger("lock_status");
                String pic = childArea.getString("pic");
                Integer areaType = childArea.getInteger("area_type");
                areaList.add(
                        LiveArea.create()
                                .setId(id)
                                .setParentId(parentId)
                                .setParentName(parentName)
                                .setOldAreaId(oldAreaId)
                                .setName(name)
                                .setActId(actId)
                                .setPkStatus(pkStatus)
                                .setHotStatus(hotStatus)
                                .setLockStatus(lockStatus)
                                .setPic(pic)
                                .setAreaType(areaType)
                );
            }
            for (LiveArea item : areaList) {
                LiveArea update = liveAreaService.getById(item.getId());
                if (update == null) {
                    liveAreaService.save(item);
                } else {
                    item.setUpdateTime(LocalDateTime.now())
                            .setVersion(update.getVersion());
                    liveAreaService.updateById(item);
                }
            }
//            boolean saved = liveAreaService.saveOrUpdateBatch(areaList);
            log.info("area:{},{}", pId, pName);
        }
    }
}
