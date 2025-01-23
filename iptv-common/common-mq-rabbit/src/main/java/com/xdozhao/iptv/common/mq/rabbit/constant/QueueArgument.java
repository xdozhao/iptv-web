package com.xdozhao.iptv.common.mq.rabbit.constant;

/**
 * 队列配置参数
 *
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 10:38
 */
public class QueueArgument {
    /**
     * How long a message published to a queue can live before it is discarded (milliseconds).
     * <p>
     * (Sets the "x-message-ttl" argument.)
     * <p>
     * Type: Number
     */
    public static final String MESSAGE_TTL = "x-message-ttl";
    /**
     * How long a queue can be unused for before it is automatically deleted (milliseconds).
     * <p>
     * (Sets the "x-expires" argument.)
     * <p>
     * Type: Number
     */
    public static final String AUTO_EXPIRE = "x-expires";
    /**
     * Sets the queue overflow behaviour. This determines what happens to messages when the maximum length of a queue is
     * reached. Valid values are drop-head, reject-publish or reject-publish-dlx. The quorum queue type only supports
     * drop-head and reject-publish.
     * <p>
     * Type: String
     */
    public static final String OVERFLOW_BEHAVIOUR = "x-overflow";
    /**
     * If set, makes sure only one consumer at a time consumes from the queue and fails over to another registered
     * consumer in case the active one is cancelled or dies.
     * <p>
     * (Sets the "x-single-active-consumer" argument.)
     * <p>
     * Type: Boolean
     */
    public static final String SINGLE_ACTIVE_CONSUMER = "x-single-active-consumer";
    /**
     * Optional name of an exchange to which messages will be republished if they are rejected or expire.
     * <p>
     * (Sets the "x-dead-letter-exchange" argument.)
     * <p>
     * Type: String
     */
    public static final String DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    /**
     * Optional replacement routing key to use when a message is dead-lettered. If this is not set, the message's
     * original routing key will be used.
     * <p>
     * (Sets the "x-dead-letter-routing-key" argument.)
     * <p>
     * Type: String
     */
    public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
    /**
     * How many (ready) messages a queue can contain before it starts to drop them from its head.
     * <p>
     * (Sets the "x-max-length" argument.)
     * <p>
     * Type: Number
     */
    public static final String MAX_LENGTH = "x-max-length";
    /**
     * Total body size for ready messages a queue can contain before it starts to drop them from its head.
     * <p>
     * (Sets the "x-max-length-bytes" argument.)
     * <p>
     * Type: Number
     */
    public static final String MAX_LENGTH_BYTES = "x-max-length-bytes";
    /**
     * Maximum number of priority levels for the queue to support; if not set, the queue will not support message
     * priorities.
     * <p>
     * (Sets the "x-max-priority" argument.)
     * <p>
     * Type: Number
     */
    public static final String MAXIMUM_PRIORITY = "x-max-priority";
    /**
     * Set the queue into lazy mode, keeping as many messages as possible on disk to reduce RAM usage; if not set, the
     * queue will keep an in-memory cache to deliver messages as fast as possible.
     * <p>
     * (Sets the "x-queue-mode" argument.)
     * <p>
     * Type: String
     */
    public static final String LAZY_MODE = "x-queue-mode";
    /**
     * Set the queue into master location mode, determining the rule by which the queue master is located when declared
     * on a cluster of nodes.
     * <p>
     * (Sets the "x-queue-master-locator" argument.)
     * <p>
     * Type: String
     */
    public static final String MASTER_LOCATOR = "x-queue-master-locator";

}
