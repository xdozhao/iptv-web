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
    status      varchar(2) default '0' comment '状态',
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
