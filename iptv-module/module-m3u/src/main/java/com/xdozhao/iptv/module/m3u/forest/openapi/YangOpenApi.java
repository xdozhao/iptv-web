package com.xdozhao.iptv.module.m3u.forest.openapi;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.http.ForestResponse;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/8 14:34
 */
@BaseRequest(baseURL = "https://tv.iill.top", userAgent = "okhttp")
public interface YangOpenApi {
    /**
     * <a href="https://yang-1989.eu.org">电视直播</a>
     * @return
     */
    @Get(url = "/m3u/Gather")
    ForestResponse<String> gather();
    /**
     * <a href="https://yang-1989.eu.org">网络直播</a>
     * @return
     */
    @Get(url = "/m3u/Live")
    ForestResponse<String> live();
}
