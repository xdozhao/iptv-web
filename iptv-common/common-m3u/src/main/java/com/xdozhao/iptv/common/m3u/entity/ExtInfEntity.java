package com.xdozhao.iptv.common.m3u.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * #EXTINF标签 基本格式
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/6 10:46
 */
@Getter
@Setter
@ToString
public class ExtInfEntity {
    /**
     * 可以为十进制的整型或者浮点型，其值必须小于或等于 EXT-X-TARGETDURATION 指定的值。
     * <p>
     * 注：建议始终使用浮点型指定时长，这可以让客户端在定位流时，减少四舍五入错误。
     * <p>
     * 但是如果兼容版本号 EXT-X-VERSION 小于 3，那么必须使用整型。
     */
    private BigDecimal duration;

    /**
     * 媒体文件的标题或描述，可以为空。
     */
    private String title;
    /**
     * 媒体文件路径
     */
    private String url;
}
