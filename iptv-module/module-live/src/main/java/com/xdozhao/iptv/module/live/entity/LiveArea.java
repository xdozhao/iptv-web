package com.xdozhao.iptv.module.live.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * bilibili直播分区 实体类。
 *
 * @author zhaoxd
 * @since 18
 */
@Accessors(chain = true)
@Data(staticConstructor = "create")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "bilibili直播分区")
@Table("b_live_area")
public class LiveArea extends Model<LiveArea> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    @Schema(description = "主键id")
    private Integer id;

    /**
     * 父分区id
     */
    @Schema(description = "父分区id")
    private Integer parentId;

    /**
     * 父分区名
     */
    @Schema(description = "父分区名")
    private String parentName;

    /**
     * 旧版分区id
     */
    @Schema(description = "旧版分区id")
    private Integer oldAreaId;

    /**
     * 分区名称
     */
    @Schema(description = "分区名称")
    private String name;

    /**
     * 0，作用尚不明确
     */
    @Schema(description = "0，作用尚不明确")
    private Integer actId;

    /**
     * ？？？，作用尚不明确
     */
    @Schema(description = "？？？，作用尚不明确")
    private Integer pkStatus;

    /**
     * 是否为热门分区，0：否<br />1：是
     */
    @Schema(description = "是否为热门分区，0：否<br />1：是")
    private Integer hotStatus;

    /**
     * 0，作用尚不明确
     */
    @Schema(description = "0，作用尚不明确")
    private Integer lockStatus;

    /**
     * 子分区标志图片url
     */
    @Schema(description = "子分区标志图片url")
    private String pic;

    @Schema(description = "")
    private Integer areaType;

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
