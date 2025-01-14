package com.xdozhao.iptv.common.m3u.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * #EXTINF标签 扩展属性
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/6 10:46
 */
@Getter
@Setter
@ToString
public class ExtInfExtEntity extends ExtInfEntity {
    /**
     * EXTINF扩展属性：指定频道的唯一标识符
     */
    private String tvgId;
    /**
     * EXTINF扩展属性：指定频道名称
     */
    private String tvgName;
    /**
     * EXTINF扩展属性：指定频道logo的URL
     */
    private String tvgLogo;
    /**
     * EXTINF扩展属性：指定频道所属的组
     */
    private String groupTitle;
}
