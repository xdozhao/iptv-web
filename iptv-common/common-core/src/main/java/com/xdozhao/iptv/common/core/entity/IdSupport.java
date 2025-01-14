package com.xdozhao.iptv.common.core.entity;

/**
 * 支持ID
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/7 10:20
 */
public interface IdSupport<T> {
    /**
     * 获取主键ID
     *
     * @return id
     */
    T getId();

    /**
     * 设置主键ID
     *
     * @param id ID
     */
    void setId(T id);
}
