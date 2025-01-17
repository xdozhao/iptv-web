package com.xdozhao.iptv.module.live.forest.response.bilibili;

import lombok.Getter;
import lombok.Setter;

/**
 * Bilibili 接口 JSON响应
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/13 10:09
 */
@Getter
@Setter
public class BiliBaseResponse {
    private int code;
    private String msg;
    private String message;
    private String data;
}
