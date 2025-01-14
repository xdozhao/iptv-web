package com.xdozhao.iptv.module.generate.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;


/**
 * m3u直播源表 表定义层。
 *
 * @author zhaoxd
 * @since 8
 */
public class M3uExtInfDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * m3u直播源表
     */
    public static final M3uExtInfDef M3U_EXT_INF = new M3uExtInfDef();

    /**
     * 主键id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 媒体文件路径
     */
    public final QueryColumn URL = new QueryColumn(this, "url");

    /**
     * 标题
     */
    public final QueryColumn TITLE = new QueryColumn(this, "title");

    /**
     * 频道的唯一标识符
     */
    public final QueryColumn TVG_ID = new QueryColumn(this, "tvg_id");

    /**
     * 备注
     */
    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    /**
     * 来源
     */
    public final QueryColumn SOURCE = new QueryColumn(this, "source");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 0:未删除；1：删除
     */
    public final QueryColumn DEL_FLAG = new QueryColumn(this, "del_flag");

    /**
     * 指定频道logo的URL
     */
    public final QueryColumn TVG_LOGO = new QueryColumn(this, "tvg_logo");

    /**
     * 频道名称
     */
    public final QueryColumn TVG_NAME = new QueryColumn(this, "tvg_name");

    /**
     * 版本号
     */
    public final QueryColumn VERSION = new QueryColumn(this, "version");

    /**
     * 创建者
     */
    public final QueryColumn CREATE_BY = new QueryColumn(this, "create_by");

    /**
     * 持续时间(秒)
     */
    public final QueryColumn DURATION = new QueryColumn(this, "duration");

    /**
     * 平台
     */
    public final QueryColumn PLATFORM = new QueryColumn(this, "platform");

    /**
     * 优先级
     */
    public final QueryColumn PRIORITY = new QueryColumn(this, "priority");

    /**
     * 更新者
     */
    public final QueryColumn UPDATE_BY = new QueryColumn(this, "update_by");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 指定频道所属的组
     */
    public final QueryColumn GROUP_TITLE = new QueryColumn(this, "group_title");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, DURATION, TITLE, URL, TVG_ID, TVG_NAME, TVG_LOGO, GROUP_TITLE, STATUS, PLATFORM, SOURCE, PRIORITY, REMARK, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, DEL_FLAG, VERSION};

    public M3uExtInfDef() {
        super("", "m3u_ext_inf");
    }

    private M3uExtInfDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public M3uExtInfDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new M3uExtInfDef("", "m3u_ext_inf", alias));
    }

}
