package com.xdozhao.iptv.module.live.forest.openapi.bilibili.live;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;
import com.xdozhao.iptv.module.live.forest.response.bilibili.BiliBaseResponse;

import java.math.BigDecimal;

/**
 *  直播媒体流
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/13 09:59
 */
@BaseRequest(baseURL = "https://api.live.bilibili.com")
public interface ILiveStreamOpenApi {
    /**
     * 根据真实直播间号获取直播视频流
     *
     * @param cid      目标真实直播间号
     * @param platform 直播流格式 h5：hls方式<br />web：http-flv方式<br />默认为http-flv方式
     * @param qn       `qn`与`quality`任选其一<br />80：流畅<br />150：高清<br />400：蓝光<br />10000：原画<br />20000：4K<br />30000：杜比
     *
     * @return 根对象
     */
    @Get(url = "/room/v1/Room/playUrl")
    BiliBaseResponse playUrl(@Query("cid") BigDecimal cid, @Query("platform") String platform, @Query("qn") int qn);
}
