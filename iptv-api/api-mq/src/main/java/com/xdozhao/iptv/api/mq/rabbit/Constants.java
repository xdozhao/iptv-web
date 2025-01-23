package com.xdozhao.iptv.api.mq.rabbit;

/**
 * 队列信息
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 10:39
 */
public class Constants {
    public static class Queue {
        /**
         * b_room_info 插入数据队列 DB_BRI_I
         */
        public static final String DB_BRI_I = "db_bri_i";


        /**
         * b_room_info 更新数据队列 DB_BRI_U
         */
        public static final String DB_BRI_U = "db_bri_u";


        /**
         * 获取直播间信息 X_H_BL_L_RGI
         */
        public static final String BL_L_RGI = "bl_l_rgi";

        /**
         * 普通队列
         */
        public static final String QUEUE = "queue";

        /**
         * 直连队列1
         */
        public static final String DIRECT_QUEUE1 = "direct_queue1";

        /**
         * 直连队列2
         */
        public static final String DIRECT_QUEUE2 = "direct_queue2";


        /**
         * 订阅队列1
         */
        public static final String FANOUT_QUEUE1 = "fanout_queue1";

        /**
         * 订阅队列2
         */
        public static final String FANOUT_QUEUE2 = "fanout_queue2";


        /**
         * 订阅消费 消息类型
         */
        public static final String TOPIC_QUEUE1 = "topic_queue1";

        /**
         * 订阅消费 消息类型
         */
        public static final String TOPIC_QUEUE2 = "topic_queue2";

        /**
         * 订阅消费 消息类型
         */
        public static final String TOPIC_QUEUE3 = "topic_queue3";

        /**
         * 默认死信队列
         */
        public static final String DEFAULT_DEAD_LETTER_QUEUE = "default_dead_letter_queue";

        public static final String DEAD_LETTER_DIRECT_QUEUE = "dead_letter_direct_queue";
        public static final String DEAD_LETTER_FANOUT_QUEUE = "dead_letter_fanout_queue";
        public static final String DEAD_LETTER_TOPIC_QUEUE = "dead_letter_topic_queue";

        /**
         * 备用交换机队列
         */
        public static final String DEFAULT_ALTERNATE_EXCHANGE_QUEUE = "default_alternate_exchange_queue";

    }

    public static class Exchange {
        /**
         * room_info 数据交换机
         */
        public static final String D_LIVE_ROOM_INFO = "d_live_room_info";
        /**
         * 直连交换机通信模型
         */
        public static final String DIRECT_EXCHANGE = "direct_exchange";
        /**
         * 发布订阅模型
         */
        public static final String FANOUT_EXCHANGE = "fanout_exchange";

        /**
         * Topic 交换机通信
         */
        public static final String TOPIC_EXCHANGE = "topic_exchange";

        /**
         * 死信交换机
         */
        public static final String DEFAULT_DEAD_LETTER = "d_default_dead_letter";

        /**
         * 备用交换机队列
         */
        public static final String DEFAULT_ALTERNATE_EXCHANGE = "default_alternate_exchange";
    }

    /**
     * 路由
     */
    public static class Router {
        /**
         * 直连路由
         */
        /**
         * b_room_info 插入数据路由 X_DB_BRI_I
         */
        public static final String X_DB_BRI_I = "x_db_bri_i";
        /**
         * b_room_info 更新数据路由 X_DB_RBI_U
         */
        public static final String X_DB_RBI_U = "x_db_rbi_u";

        /**
         * 获取直播间信息 路由
         */
        public static final String X_H_BL_L_RGI = "x_h_bl_l_rgi";
        /**
         * m1消息路由
         */
        public static final String DIRECT_M1 = "m1";
        /**
         * m2消息路由
         */
        public static final String DIRECT_M2 = "m2";
        /**
         * m3消息路由
         */
        public static final String DIRECT_M3 = "m3";
        /**
         * Topic 消息路由
         */
        /**
         * Topic 模型向匹配队列发消息
         * <p>
         * #表示0个或若干个关键字，*表示一个关键字
         */
        public static final String TOPIC_START_T1 = "t1.#";
        public static final String TOPIC_CONTAIN_T1 = "#.t1.#";
        public static final String TOPIC_END_T1 = "#.t1";
        public static final String TOPIC_T1_WORD = "t1.*";
        public static final String TOPIC_WORD_T1_WORD = "*.t1.*";
        public static final String TOPIC_WORD_T1 = "*.t1";

        /**
         * 死信队列直连路由
         */
        public static final String DEAD_LETTER_ROUTER = "dead_letter_router";
        public static final String DEAD_LETTER_DIRECT = "dead_letter_direct";
        public static final String DEAD_LETTER_FANOUT = "dead_letter_fanout";
        public static final String DEAD_LETTER_TOPIC = "dead_letter_topic";

    }
}
