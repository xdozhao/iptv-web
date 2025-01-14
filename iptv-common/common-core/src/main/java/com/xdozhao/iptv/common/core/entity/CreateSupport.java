package com.xdozhao.iptv.common.core.entity;

import java.time.ZonedDateTime;

/**
 * 支持 Create
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/7 10:20
 */
public interface CreateSupport<T> {

    T getCreateBy();

    void setCreateBy(T createBy);

    ZonedDateTime getCreateTime();

    void setCreateTime(ZonedDateTime createTime);
}
