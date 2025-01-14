package com.xdozhao.iptv.module.m3u.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * m3u直播源表 实体类。
 *
 * @author zhaoxd
 * @since 8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "m3u直播源表")
@Table("m3u_ext_inf")
public class M3uExtInf extends Model<M3uExtInf> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    @Schema(description = "主键id")
    private String id;

    /**
     * 持续时间(秒)
     */
    @Schema(description = "持续时间(秒)")
    private BigDecimal duration;

    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;

    /**
     * 媒体文件路径
     */
    @Schema(description = "媒体文件路径")
    private String url;

    /**
     * 频道的唯一标识符
     */
    @Schema(description = "频道的唯一标识符")
    private String tvgId;

    /**
     * 频道名称
     */
    @Schema(description = "频道名称")
    private String tvgName;

    /**
     * 指定频道logo的URL
     */
    @Schema(description = "指定频道logo的URL")
    private String tvgLogo;

    /**
     * 指定频道所属的组
     */
    @Schema(description = "指定频道所属的组")
    private String groupTitle;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private String status;

    /**
     * 平台
     */
    @Schema(description = "平台")
    private String platform;

    /**
     * 来源
     */
    @Schema(description = "来源")
    private String source;

    /**
     * 优先级
     */
    @Schema(description = "优先级")
    private Integer priority;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 创建者
     */
    @Schema(description = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @Schema(description = "更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 0:未删除；1：删除
     */
    @Schema(description = "0:未删除；1：删除")
    private String delFlag;

    /**
     * 版本号
     */
    @Schema(description = "版本号")
    private Integer version;

}
