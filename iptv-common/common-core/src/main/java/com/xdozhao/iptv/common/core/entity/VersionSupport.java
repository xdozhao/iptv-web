package com.xdozhao.iptv.common.core.entity;

/**
 * 支持逻辑删除的Entity
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/7 10:20
 */
public interface VersionSupport<T> {

    T getVersion() ;

    void setVersion(T version);
}
