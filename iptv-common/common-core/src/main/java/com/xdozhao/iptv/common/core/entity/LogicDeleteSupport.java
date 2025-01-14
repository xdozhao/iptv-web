package com.xdozhao.iptv.common.core.entity;

/**
 * 支持逻辑删除的
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/7 10:20
 */
public interface LogicDeleteSupport<T> {
    T getDelFlag();

    void setDelFlag(T delFlag) ;
}
