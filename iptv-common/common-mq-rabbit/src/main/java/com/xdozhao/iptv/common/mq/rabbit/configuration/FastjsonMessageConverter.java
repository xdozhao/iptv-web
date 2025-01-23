package com.xdozhao.iptv.common.mq.rabbit.configuration;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.xdozhao.iptv.common.mq.rabbit.enitiy.Mail;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 09:48
 */
public class FastjsonMessageConverter extends AbstractMessageConverter {
    /**
     * This method creates a RabbitMQ message from a given Java object using Fastjson.
     *
     * @param o                 The Java object to be converted into a RabbitMQ message.
     * @param messageProperties The properties of the RabbitMQ message.
     *
     * @return The RabbitMQ message created from the given Java object.
     *
     * @throws MessageConversionException If the Java object cannot be converted into a RabbitMQ message.
     */
    @Override
    protected Message createMessage(Object o, MessageProperties messageProperties) {
        byte[] bytes = JSON.toJSONBytes(o);
        messageProperties.setContentType("application/json");
        messageProperties.setContentEncoding("UTF-8");
        messageProperties.setContentLength(bytes.length);
        return new Message(bytes, messageProperties);
    }

    /**
     * Converts a RabbitMQ message to a Java object using Fastjson.
     *
     * @param message The RabbitMQ message to be converted.
     *
     * @return The Java object represented by the message.
     *
     * @throws MessageConversionException If the message cannot be converted to a Java object.
     */
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        byte[] bytes = message.getBody();
        return JSON.parseObject(bytes, Mail.class, JSONReader.Feature.SupportAutoType);
    }
}
