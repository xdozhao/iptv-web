package com.xdozhao.iptv.module.m3u.forest.openapi;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.http.ForestResponse;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/8 11:38
 */
@BaseRequest(baseURL = "https://live.fanmingming.cn", userAgent = "okhttp")
public interface FanmingmingOpenApi {
    /**
     * <a href="https://live.fanmingming.com">fanmingming直播源</a>
     *
     * @return
     */
    @Get(url = "/tv/m3u/ipv6.m3u")
    ForestResponse<String> ipv6();

}
