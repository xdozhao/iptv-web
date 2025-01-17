package com.xdozhao.iptv.module.live.forest.openapi.bilibili.live;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;
import com.xdozhao.iptv.module.live.forest.response.bilibili.BiliBaseResponse;

/**
 * 直播间分区
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/13 17:11
 */
@BaseRequest(baseURL = "https://api.live.bilibili.com")
public interface ILiveAreaOpenApi {
    /**
     * 获取全部直播间分区列表
     *
     *
     * @return 根对象
     */
    @Get(url = "/room/v1/Area/getList")
    BiliBaseResponse getList();

    /**
     * 直播列表
     * @param areaId [0-6] （1-6对应区号，0为全站）
     * @param page 页数
     * @param pageSize [1-99] 每页显示数量
     * @param sortType online：最热直播; live_time：最新开播
     * @return 根对象
     */
    @Get(url = "/room/v3/area/getRoomList")
    BiliBaseResponse getRoomList(@Query("parent_area_id") int areaId,@Query("page") int page,@Query("page_size") int pageSize,@Query("sort_type") String sortType);
}
