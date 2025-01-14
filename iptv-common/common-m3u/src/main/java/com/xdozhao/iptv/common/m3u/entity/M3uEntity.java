package com.xdozhao.iptv.common.m3u.entity;

import com.xdozhao.iptv.common.core.constant.M3u8Constant;

import java.util.List;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/6 10:54
 */
public class M3uEntity {
    private String header = M3u8Constant.EXTM3U;
    private List<ExtInfExtEntity> extInfList;
}
