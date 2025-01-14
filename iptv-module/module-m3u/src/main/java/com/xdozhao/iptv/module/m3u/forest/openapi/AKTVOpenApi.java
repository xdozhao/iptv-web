package com.xdozhao.iptv.module.m3u.forest.openapi;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.http.ForestResponse;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/8 15:47
 */
@BaseRequest(baseURL = "https://aktv.top", userAgent = "okhttp")
public interface AKTVOpenApi {
    /**
     * <a href="https://aktv.top">AKTV</a>
     * @return
     */
    @Get(url = "/live.m3u", headers = {"User-Agent: okhttp"})
    ForestResponse<String> live();
}
