drop database if exists `iptv`;
create
database `iptv` default character set utf8mb4 collate utf8mb4_general_ci;
use `iptv`;
-- ----------------------------
-- table structure for m3u_ext_inf
-- ----------------------------
drop table if exists `m3u_ext_inf`;
create table m3u_ext_inf
(
    id          varchar(32)                 not null comment '主键id',
    duration    DECIMAL(10, 3) default '-1' not null comment '持续时间(秒)',
    title       varchar(64)                 not null comment '标题',
    url         varchar(512) comment '媒体文件路径',
    tvg_id      varchar(64) comment '频道的唯一标识符',
    tvg_name    varchar(64) comment '频道名称',
    tvg_logo    varchar(512) comment '指定频道logo的URL',
    group_title varchar(64) comment '指定频道所属的组',
    status varchar(2) default '0' comment '状态',
    platform    varchar(64) comment '平台',
    source      varchar(64) comment '来源',
    priority    int            default 1000 comment '优先级',
    remark      varchar(255) comment '备注',
    create_by   varchar(32) comment '创建者',
    create_time datetime       default now() comment '创建时间',
    update_by   varchar(32) comment '更新者',
    update_time datetime comment '更新时间',
    del_flag    varchar(1)     default '0' comment '0:未删除；1：删除',
    version     int            default 0 comment '版本号',
    primary key (id) using btree
) comment ='m3u直播源表';

-- ----------------------------
-- table structure for b_live_area
-- ----------------------------
drop table if exists `b_live_area`;
create table b_live_area
(
    id          int unique not null comment '主键id',
    parent_id   int comment '父分区id',
    parent_name varchar(64) comment '父分区名',
    old_area_id int comment '旧版分区id',
    name        varchar(64) comment '分区名称',
    act_id      int comment '0，作用尚不明确',
    pk_status   int comment '？？？，作用尚不明确',
    hot_status  int comment '是否为热门分区，0：否<br />1：是',
    lock_status int comment '0，作用尚不明确',
    pic         varchar(256) comment '子分区标志图片url',
    area_type   int comment '',
    remark      varchar(255) comment '备注',
    create_by   varchar(32) comment '创建者',
    create_time datetime   default now() comment '创建时间',
    update_by   varchar(32) comment '更新者',
    update_time datetime comment '更新时间',
    del_flag    varchar(1) default '0' comment '0:未删除；1：删除',
    version     int        default 0 comment '版本号',
    primary key (id) using btree
) comment ='bilibili直播分区';

-- ----------------------------
-- table structure for b_room_info
-- ----------------------------
drop table if exists `b_room_info`;
create table b_room_info
(
    id      DECIMAL(16, 0) unique not null comment '主键id',
    uname                   varchar(64) comment '用户名',
    face                    varchar(256) comment '用户头像',
    room_id DECIMAL(16, 0) unique not null comment '直播间id',
    short_id                int comment '直播间短号，为0是无短号',
    attention               int comment '关注数量',
    online                  int comment '观看人数',
    is_portrait             bool comment '是否竖屏',
    description             text comment '描述',
    live_status             tinyint comment '直播状态,0：未开播<br />1：直播中<br />2：轮播中',
    area_id                 int comment '分区id',
    area_name               varchar(64) comment '分区名称',
    parent_area_id          int comment '父分区id',
    parent_area_name        varchar(64) comment '父分区名称',
    old_area_id             int comment '旧版分区id',
    background              varchar(256) comment '背景图片链接',
    title                   varchar(64) comment '标题',
    user_cover              varchar(256) comment '封面',
    keyframe                varchar(256) comment '关键帧,用于网页端悬浮展示',
    is_strict_room          bool comment '未知',
    live_time               varchar(32) comment '直播开始时间,YYYY-MM-DD HH:mm:ss',
    tags                    varchar(128) comment '标签,分隔',
    is_anchor               int comment '未知',
    room_silent_type        varchar(32) comment '禁言状态',
    room_silent_level       tinyint comment '禁言等级',
    room_silent_second      int comment '禁言时间，单位是秒',
    pardants                varchar(32) comment '未知',
    area_pardants           varchar(32) comment '未知',
    hot_words_status        tinyint comment '热词状态',
    verify                  varchar(32) comment '未知',
    up_session              varchar(32) comment '未知',
    pk_status               tinyint comment 'pk状态',
    pk_id                   int comment 'pk id',
    battle_id               int comment '未知',
    allow_change_area_time  int comment '未知',
    allow_upload_cover_time int comment '未知',
    remark                  varchar(255) comment '备注',
    create_by               varchar(32) comment '创建者',
    create_time             datetime   default now() comment '创建时间',
    update_by               varchar(32) comment '更新者',
    update_time             datetime comment '更新时间',
    del_flag                varchar(1) default '0' comment '0:未删除；1：删除',
    version                 int        default 0 comment '版本号',
    primary key (id) using btree
) comment ='bilibili直播间信息';
