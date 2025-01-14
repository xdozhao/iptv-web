package com.xdozhao.iptv.module.live.forest.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Bilibili 接口 JSON响应
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/13 10:09
 */
@Getter
@Setter
@ToString
public class BiliBaseResponse {
    private int code;
    private String msg;
    private String message;
    private String data;
}
