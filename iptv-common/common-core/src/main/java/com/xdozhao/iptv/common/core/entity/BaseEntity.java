package com.xdozhao.iptv.common.core.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.core.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/7 10:20
 */
@Getter
@Setter
public class BaseEntity extends Model<BaseEntity> implements IdSupport<String>, LogicDeleteSupport<String>, VersionSupport<Integer>, CreateSupport<String>, UpdateSupport<String> {
    private static final long serialVersionUID = 1L;
    /**
     * 任务ID
     */
    @Id
    private String id;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private ZonedDateTime createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private ZonedDateTime updateTime;
    /**
     * 0:未删除；1：删除
     */
    @Column(isLogicDelete = true)
    private String delFlag;
    /**
     * 版本号
     */
    @Column(version = true)
    private Integer version;
}
