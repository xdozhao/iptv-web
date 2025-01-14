package com.xdozhao.iptv.common.core.entity;

import java.time.ZonedDateTime;

/**
 * 支持ID的Entity
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/7 10:20
 */
public interface UpdateSupport<T> {
    T getUpdateBy();

    void setUpdateBy(T updateBy);

    ZonedDateTime getUpdateTime();

    void setUpdateTime(ZonedDateTime updateTime);
}
