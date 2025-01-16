package com.xdozhao.iptv.module.live.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * bilibili直播间信息 实体类。
 *
 * @author zhaoxd
 * @since 18
 */
@Accessors(chain = true)
@Data(staticConstructor = "create")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "bilibili直播间信息")
@Table("b_room_info")
public class RoomInfo extends Model<RoomInfo> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    @Schema(description = "主键id")
    private BigDecimal id;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String uname;

    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private String face;

    /**
     * 直播间id
     */
    @Schema(description = "直播间id")
    private BigDecimal roomId;

    /**
     * 直播间短号，为0是无短号
     */
    @Schema(description = "直播间短号，为0是无短号")
    private Integer shortId;

    /**
     * 关注数量
     */
    @Schema(description = "关注数量")
    private Integer attention;

    /**
     * 观看人数
     */
    @Schema(description = "观看人数")
    private Integer online;

    /**
     * 是否竖屏
     */
    @Schema(description = "是否竖屏")
    private Boolean isPortrait;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;

    /**
     * 直播状态,0：未开播<br />1：直播中<br />2：轮播中
     */
    @Schema(description = "直播状态,0：未开播<br />1：直播中<br />2：轮播中")
    private Integer liveStatus;

    /**
     * 分区id
     */
    @Schema(description = "分区id")
    private Integer areaId;

    /**
     * 分区名称
     */
    @Schema(description = "分区名称")
    private String areaName;

    /**
     * 父分区id
     */
    @Schema(description = "父分区id")
    private Integer parentAreaId;

    /**
     * 父分区名称
     */
    @Schema(description = "父分区名称")
    private String parentAreaName;

    /**
     * 旧版分区id
     */
    @Schema(description = "旧版分区id")
    private Integer oldAreaId;

    /**
     * 背景图片链接
     */
    @Schema(description = "背景图片链接")
    private String background;

    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;

    /**
     * 封面
     */
    @Schema(description = "封面")
    private String userCover;

    /**
     * 关键帧,用于网页端悬浮展示
     */
    @Schema(description = "关键帧,用于网页端悬浮展示")
    private String keyframe;

    /**
     * 未知
     */
    @Schema(description = "未知")
    private Boolean isStrictRoom;

    /**
     * 直播开始时间,YYYY-MM-DD HH:mm:ss
     */
    @Schema(description = "直播开始时间,YYYY-MM-DD HH:mm:ss")
    private String liveTime;

    /**
     * 标签,分隔
     */
    @Schema(description = "标签,分隔")
    private String tags;

    /**
     * 未知
     */
    @Schema(description = "未知")
    private Integer isAnchor;

    /**
     * 禁言状态
     */
    @Schema(description = "禁言状态")
    private String roomSilentType;

    /**
     * 禁言等级
     */
    @Schema(description = "禁言等级")
    private Integer roomSilentLevel;

    /**
     * 禁言时间，单位是秒
     */
    @Schema(description = "禁言时间，单位是秒")
    private Integer roomSilentSecond;

    /**
     * 未知
     */
    @Schema(description = "未知")
    private String pardants;

    /**
     * 未知
     */
    @Schema(description = "未知")
    private String areaPardants;

    /**
     * 热词状态
     */
    @Schema(description = "热词状态")
    private Integer hotWordsStatus;

    /**
     * 未知
     */
    @Schema(description = "未知")
    private String verify;

    /**
     * 未知
     */
    @Schema(description = "未知")
    private String upSession;

    /**
     * pk状态
     */
    @Schema(description = "pk状态")
    private Integer pkStatus;

    /**
     * pk id
     */
    @Schema(description = "pk id")
    private Integer pkId;

    /**
     * 未知
     */
    @Schema(description = "未知")
    private Integer battleId;

    /**
     * 未知
     */
    @Schema(description = "未知")
    private Integer allowChangeAreaTime;

    /**
     * 未知
     */
    @Schema(description = "未知")
    private Integer allowUploadCoverTime;

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
