package com.xdozhao.iptv.common.mq.rabbit.enitiy;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 11:41
 */
@Getter
@Setter
@Builder
public class TopicMail<T extends Serializable> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    private String id;
    private LocalDateTime sendDate;
    private T data;
    private String routingKey;
}