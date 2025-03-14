package com.xdozhao.iptv.common.mq.rabbit.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.ReceiveAndReplyCallback;
import org.springframework.amqp.core.ReplyToAddressCallback;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SmartMessageConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

/**
 * @author zhaoxd
 * @version 1.0.0
 * @date 2025/1/21 11:38
 */
@Slf4j
@Service
@AllArgsConstructor
public class RabbitService {

    private final RabbitTemplate rabbitTemplateService;


    /**
     * Execute the callback with a channel and reliably close the channel afterwards.
     *
     * @param action the call back.
     *
     * @return the result from the {@link ChannelCallback#doInRabbit(Channel)}.
     *
     * @throws AmqpException if one occurs.
     */
    public <T> T execute(ChannelCallback<T> action) throws AmqpException {
        return rabbitTemplateService.execute(action);
    }

    /**
     * Invoke operations on the same channel. If callbacks are needed, both callbacks must be supplied.
     *
     * @param action the callback.
     * @param acks   a confirm callback for acks.
     * @param nacks  a confirm callback for nacks.
     *
     * @return the result of the action method.
     *
     * @since 2.1
     */
    public <T> T invoke(RabbitOperations.OperationsCallback<T> action, ConfirmCallback acks, ConfirmCallback nacks) {
        return rabbitTemplateService.invoke(action, acks, nacks);
    }

    /**
     * Delegate to the underlying dedicated channel to wait for confirms. The connection factory must be configured for
     * publisher confirms and this method must be called within the scope of an {@link #invoke(OperationsCallback)}
     * operation. Requires {@code CachingConnectionFactory#setPublisherConfirms(true)}.
     *
     * @param timeout the timeout
     *
     * @return true if acks and no nacks are received.
     *
     * @throws AmqpException if one occurs.
     * @see Channel#waitForConfirms(long)
     * @since 2.0
     */
    public boolean waitForConfirms(long timeout) throws AmqpException {
        return rabbitTemplateService.waitForConfirms(timeout);
    }

    /**
     * Delegate to the underlying dedicated channel to wait for confirms. The connection factory must be configured for
     * publisher confirms and this method must be called within the scope of an {@link #invoke(OperationsCallback)}
     * operation. Requires {@code CachingConnectionFactory#setPublisherConfirms(true)}.
     *
     * @param timeout the timeout
     *
     * @throws AmqpException if one occurs.
     * @see Channel#waitForConfirmsOrDie(long)
     * @since 2.0
     */
    public void waitForConfirmsOrDie(long timeout) throws AmqpException {
        rabbitTemplateService.waitForConfirmsOrDie(timeout);
    }

    /**
     * Return the connection factory for this operations.
     *
     * @return the connection factory.
     *
     * @since 2.0
     */
    public ConnectionFactory getConnectionFactory() {
        return rabbitTemplateService.getConnectionFactory();
    }

    /**
     * Send a message to a specific exchange with a specific routing key.
     *
     * @param exchange        the name of the exchange
     * @param routingKey      the routing key
     * @param message         a message to send
     * @param correlationData data to correlate publisher confirms.
     *
     * @throws AmqpException if there is a problem
     */
    public void send(String exchange, String routingKey, Message message, CorrelationData correlationData) throws AmqpException {
        rabbitTemplateService.send(exchange, routingKey, message, correlationData);
    }

    /**
     * Convert a Java object to an Amqp {@link Message} and send it to a default exchange with a default routing key.
     *
     * @param message         a message to send
     * @param correlationData data to correlate publisher confirms.
     *
     * @throws AmqpException if there is a problem
     */
    public void correlationConvertAndSend(Object message, CorrelationData correlationData) throws AmqpException {
        rabbitTemplateService.correlationConvertAndSend(message, correlationData);
    }

    /**
     * Convert a Java object to an Amqp {@link Message} and send it to a default exchange with a specific routing key.
     *
     * @param routingKey      the routing key
     * @param message         a message to send
     * @param correlationData data to correlate publisher confirms.
     *
     * @throws AmqpException if there is a problem
     */
    public void convertAndSend(String routingKey, Object message, CorrelationData correlationData) throws AmqpException {
        rabbitTemplateService.convertAndSend(routingKey, message, correlationData);
    }

    /**
     * Convert a Java object to an Amqp {@link Message} and send it to a specific exchange with a specific routing key.
     *
     * @param exchange        the name of the exchange
     * @param routingKey      the routing key
     * @param message         a message to send
     * @param correlationData data to correlate publisher confirms.
     *
     * @throws AmqpException if there is a problem
     */
    public void convertAndSend(String exchange, String routingKey, Object message, CorrelationData correlationData) throws AmqpException {
        rabbitTemplateService.convertAndSend(exchange, routingKey, message, correlationData);
    }

    /**
     * Convert a Java object to an Amqp {@link Message} and send it to a default exchange with a default routing key.
     *
     * @param message              a message to send
     * @param messagePostProcessor a processor to apply to the message before it is sent
     * @param correlationData      data to correlate publisher confirms.
     *
     * @throws AmqpException if there is a problem
     */
    public void convertAndSend(Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) throws AmqpException {
        rabbitTemplateService.convertAndSend(message, messagePostProcessor);
    }

    /**
     * Convert a Java object to an Amqp {@link Message} and send it to a default exchange with a specific routing key.
     *
     * @param routingKey           the routing key
     * @param message              a message to send
     * @param messagePostProcessor a processor to apply to the message before it is sent
     * @param correlationData      data to correlate publisher confirms.
     *
     * @throws AmqpException if there is a problem
     */
    public void convertAndSend(String routingKey, Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) throws AmqpException {
        rabbitTemplateService.convertAndSend(routingKey, messagePostProcessor, messagePostProcessor, correlationData);
    }

    /**
     * Convert a Java object to an Amqp {@link Message} and send it to a specific exchange with a specific routing key.
     *
     * @param exchange             the name of the exchange
     * @param routingKey           the routing key
     * @param message              a message to send
     * @param messagePostProcessor a processor to apply to the message before it is sent
     * @param correlationData      data to correlate publisher confirms.
     *
     * @throws AmqpException if there is a problem
     */
    public void convertAndSend(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) throws AmqpException {
        rabbitTemplateService.convertAndSend(exchange, routingKey, message, messagePostProcessor, correlationData);
    }

    /**
     * Basic RPC pattern with conversion. Send a Java object converted to a message to a default exchange with a default
     * routing key and attempt to receive a response, converting that to a Java object. Implementations will normally
     * set the reply-to header to an exclusive queue and wait up for some time limited by a timeout.
     *
     * @param message         a message to send.
     * @param correlationData data to correlate publisher confirms.
     *
     * @return the response if there is one
     *
     * @throws AmqpException if there is a problem
     */
    public Object convertSendAndReceive(Object message, CorrelationData correlationData) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceive(message, correlationData);
    }

    /**
     * Basic RPC pattern with conversion. Send a Java object converted to a message to a default exchange with a
     * specific routing key and attempt to receive a response, converting that to a Java object. Implementations will
     * normally set the reply-to header to an exclusive queue and wait up for some time limited by a timeout.
     *
     * @param routingKey      the routing key
     * @param message         a message to send
     * @param correlationData data to correlate publisher confirms.
     *
     * @return the response if there is one
     *
     * @throws AmqpException if there is a problem
     */
    public Object convertSendAndReceive(String routingKey, Object message, CorrelationData correlationData) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceive(routingKey, message, correlationData);
    }

    /**
     * Basic RPC pattern with conversion. Send a Java object converted to a message to a specific exchange with a
     * specific routing key and attempt to receive a response, converting that to a Java object. Implementations will
     * normally set the reply-to header to an exclusive queue and wait up for some time limited by a timeout.
     *
     * @param exchange        the name of the exchange
     * @param routingKey      the routing key
     * @param message         a message to send
     * @param correlationData data to correlate publisher confirms.
     *
     * @return the response if there is one
     *
     * @throws AmqpException if there is a problem
     */
    public Object convertSendAndReceive(String exchange, String routingKey, Object message, CorrelationData correlationData) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceive(exchange, routingKey, message, correlationData);
    }

    /**
     * Basic RPC pattern with conversion. Send a Java object converted to a message to a default exchange with a default
     * routing key and attempt to receive a response, converting that to a Java object. Implementations will normally
     * set the reply-to header to an exclusive queue and wait up for some time limited by a timeout.
     *
     * @param message              a message to send
     * @param messagePostProcessor a processor to apply to the message before it is sent
     * @param correlationData      data to correlate publisher confirms.
     *
     * @return the response if there is one
     *
     * @throws AmqpException if there is a problem
     */
    public Object convertSendAndReceive(Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceive(message, messagePostProcessor, correlationData);
    }

    /**
     * Basic RPC pattern with conversion. Send a Java object converted to a message to a default exchange with a
     * specific routing key and attempt to receive a response, converting that to a Java object. Implementations will
     * normally set the reply-to header to an exclusive queue and wait up for some time limited by a timeout.
     *
     * @param routingKey           the routing key
     * @param message              a message to send
     * @param messagePostProcessor a processor to apply to the message before it is sent
     * @param correlationData      data to correlate publisher confirms.
     *
     * @return the response if there is one
     *
     * @throws AmqpException if there is a problem
     */
    public Object convertSendAndReceive(String routingKey, Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceive(routingKey, message, messagePostProcessor, correlationData);
    }

    /**
     * Basic RPC pattern with conversion. Send a Java object converted to a message to a specific exchange with a
     * specific routing key and attempt to receive a response, converting that to a Java object. Implementations will
     * normally set the reply-to header to an exclusive queue and wait up for some time limited by a timeout.
     *
     * @param exchange             the name of the exchange
     * @param routingKey           the routing key
     * @param message              a message to send
     * @param messagePostProcessor a processor to apply to the message before it is sent
     * @param correlationData      data to correlate publisher confirms.
     *
     * @return the response if there is one
     *
     * @throws AmqpException if there is a problem
     */
    private Object convertSendAndReceive(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceive(exchange, routingKey, message, messagePostProcessor, correlationData);
    }

    /**
     * Basic RPC pattern with conversion. Send a Java object converted to a message to a default exchange with a default
     * routing key and attempt to receive a response, converting that to a Java object. Implementations will normally
     * set the reply-to header to an exclusive queue and wait up for some time limited by a timeout. Requires a
     * {@link SmartMessageConverter}.
     *
     * @param message         a message to send.
     * @param correlationData data to correlate publisher confirms.
     * @param responseType    the type to convert the reply to.
     *
     * @return the response if there is one.
     *
     * @throws AmqpException if there is a problem.
     */
    public <T> T convertSendAndReceiveAsType(Object message, CorrelationData correlationData, ParameterizedTypeReference<T> responseType) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceiveAsType(message, correlationData, responseType);
    }

    /**
     * Basic RPC pattern with conversion. Send a Java object converted to a message to a default exchange with a
     * specific routing key and attempt to receive a response, converting that to a Java object. Implementations will
     * normally set the reply-to header to an exclusive queue and wait up for some time limited by a timeout. Requires a
     * {@link SmartMessageConverter}.
     *
     * @param routingKey      the routing key
     * @param message         a message to send
     * @param correlationData data to correlate publisher confirms.
     * @param responseType    the type to convert the reply to.
     *
     * @return the response if there is one
     *
     * @throws AmqpException if there is a problem
     */
    public <T> T convertSendAndReceiveAsType(String routingKey, Object message, CorrelationData correlationData, ParameterizedTypeReference<T> responseType) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceiveAsType(routingKey, message, correlationData, responseType);
    }

    /**
     * Basic RPC pattern with conversion. Send a Java object converted to a message to a default exchange with a default
     * routing key and attempt to receive a response, converting that to a Java object. Implementations will normally
     * set the reply-to header to an exclusive queue and wait up for some time limited by a timeout. Requires a
     * {@link SmartMessageConverter}.
     *
     * @param message              a message to send
     * @param messagePostProcessor a processor to apply to the message before it is sent
     * @param correlationData      data to correlate publisher confirms.
     * @param responseType         the type to convert the reply to.
     *
     * @return the response if there is one
     *
     * @throws AmqpException if there is a problem
     */
    public <T> T convertSendAndReceiveAsType(Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData, ParameterizedTypeReference<T> responseType) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceiveAsType(message, messagePostProcessor, correlationData, responseType);
    }

    /**
     * Basic RPC pattern with conversion. Send a Java object converted to a message to a default exchange with a
     * specific routing key and attempt to receive a response, converting that to a Java object. Implementations will
     * normally set the reply-to header to an exclusive queue and wait up for some time limited by a timeout. Requires a
     * {@link SmartMessageConverter}.
     *
     * @param routingKey           the routing key
     * @param message              a message to send
     * @param messagePostProcessor a processor to apply to the message before it is sent
     * @param correlationData      data to correlate publisher confirms.
     * @param responseType         the type to convert the reply to.
     *
     * @return the response if there is one
     *
     * @throws AmqpException if there is a problem
     */
    public <T> T convertSendAndReceiveAsType(String routingKey, Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData, ParameterizedTypeReference<T> responseType) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceiveAsType(routingKey, message, messagePostProcessor, correlationData, responseType);
    }

    /**
     * Basic RPC pattern with conversion. Send a Java object converted to a message to a specific exchange with a
     * specific routing key and attempt to receive a response, converting that to a Java object. Implementations will
     * normally set the reply-to header to an exclusive queue and wait up for some time limited by a timeout. Requires a
     * {@link SmartMessageConverter}.
     *
     * @param exchange             the name of the exchange
     * @param routingKey           the routing key
     * @param message              a message to send
     * @param messagePostProcessor a processor to apply to the message before it is sent
     * @param correlationData      data to correlate publisher confirms.
     * @param responseType         the type to convert the reply to.
     *
     * @return the response if there is one
     *
     * @throws AmqpException if there is a problem
     */
    public <T> T convertSendAndReceiveAsType(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData, ParameterizedTypeReference<T> responseType) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceiveAsType(exchange, routingKey, message, messagePostProcessor, correlationData, responseType);
    }

    public void send(Message message) throws AmqpException {
        rabbitTemplateService.send(message);
    }

    public void send(String routingKey, Message message) throws AmqpException {
        rabbitTemplateService.send(routingKey, message);
    }

    public void send(final String exchange, final String routingKey, final Message message) throws AmqpException {
        rabbitTemplateService.send(exchange, routingKey, message);
    }

    public void convertAndSend(Object object) throws AmqpException {
        rabbitTemplateService.convertAndSend(object);
    }

    public void convertAndSend(String routingKey, final Object object) throws AmqpException {
        rabbitTemplateService.convertAndSend(routingKey, object);
    }

    public void convertAndSend(String exchange, String routingKey, final Object object) throws AmqpException {
        rabbitTemplateService.convertAndSend(exchange, routingKey, object);
    }

    public void convertAndSend(Object message, MessagePostProcessor messagePostProcessor) throws AmqpException {
        rabbitTemplateService.convertAndSend(message, messagePostProcessor);
    }

    public void convertAndSend(String routingKey, Object message, MessagePostProcessor messagePostProcessor) throws AmqpException {
        rabbitTemplateService.convertAndSend(routingKey, message, messagePostProcessor);
    }

    public void convertAndSend(String exchange, String routingKey, final Object message, final MessagePostProcessor messagePostProcessor) throws AmqpException {
        rabbitTemplateService.convertAndSend(exchange, routingKey, message, messagePostProcessor, null);
    }

    public Message receive() throws AmqpException {
        return rabbitTemplateService.receive();
    }

    public Message receive(String queueName) throws AmqpException {
        return rabbitTemplateService.receive(queueName);
    }

    public Message receive(long timeoutMillis) throws AmqpException {
        return rabbitTemplateService.receive(timeoutMillis);
    }

    public Message receive(final String queueName, final long timeoutMillis) throws AmqpException {
        return rabbitTemplateService.receive(queueName, timeoutMillis);
    }

    public Object receiveAndConvert() throws AmqpException {
        return rabbitTemplateService.receiveAndConvert();
    }

    public Object receiveAndConvert(String queueName) throws AmqpException {
        return rabbitTemplateService.receiveAndConvert(queueName);
    }

    public Object receiveAndConvert(long timeoutMillis) throws AmqpException {
        return rabbitTemplateService.receiveAndConvert(timeoutMillis);
    }

    public Object receiveAndConvert(String queueName, long timeoutMillis) throws AmqpException {
        return rabbitTemplateService.receiveAndConvert(queueName, timeoutMillis);
    }

    public <T> T receiveAndConvert(ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplateService.receiveAndConvert(parameterizedTypeReference);
    }

    public <T> T receiveAndConvert(String queueName, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplateService.receiveAndConvert(queueName, parameterizedTypeReference);
    }

    public <T> T receiveAndConvert(long timeoutMillis, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplateService.receiveAndConvert(timeoutMillis, parameterizedTypeReference);
    }

    public <T> T receiveAndConvert(String queueName, long timeoutMillis, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplateService.receiveAndConvert(queueName, timeoutMillis, parameterizedTypeReference);
    }

    public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback) throws AmqpException {
        return rabbitTemplateService.receiveAndReply(receiveAndReplyCallback);
    }

    public <R, S> boolean receiveAndReply(final String queueName, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback) throws AmqpException {
        return rabbitTemplateService.receiveAndReply(queueName, receiveAndReplyCallback);
    }

    public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, final String exchange, final String routingKey) throws AmqpException {
        return rabbitTemplateService.receiveAndReply(receiveAndReplyCallback, exchange, routingKey);
    }

    public <R, S> boolean receiveAndReply(final String queueName, ReceiveAndReplyCallback<R, S> callback,
                                          final String replyExchange, final String replyRoutingKey) throws AmqpException {
        return rabbitTemplateService.receiveAndReply(queueName, callback, replyExchange, replyRoutingKey);
    }

    public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, ReplyToAddressCallback<S> replyToAddressCallback) throws AmqpException {
        return rabbitTemplateService.receiveAndReply(receiveAndReplyCallback, replyToAddressCallback);
    }

    public <R, S> boolean receiveAndReply(String queueName, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, ReplyToAddressCallback<S> replyToAddressCallback) throws AmqpException {
        return rabbitTemplateService.receiveAndReply(queueName, receiveAndReplyCallback, replyToAddressCallback);
    }

    public Message sendAndReceive(Message message) throws AmqpException {
        return rabbitTemplateService.sendAndReceive(message);
    }

    public Message sendAndReceive(String routingKey, Message message) throws AmqpException {
        return rabbitTemplateService.sendAndReceive(routingKey, message);
    }

    public Message sendAndReceive(final String exchange, final String routingKey, final Message message) throws AmqpException {
        return rabbitTemplateService.sendAndReceive(exchange, routingKey, message);
    }

    public Object convertSendAndReceive(final Object message) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceive(message);
    }

    public Object convertSendAndReceive(final String routingKey, final Object message) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceive(routingKey, message);
    }

    public Object convertSendAndReceive(final String exchange, final String routingKey, final Object message) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceive(exchange, routingKey, message);
    }

    public Object convertSendAndReceive(final Object message, final MessagePostProcessor messagePostProcessor) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceive(message, messagePostProcessor);
    }

    public Object convertSendAndReceive(final String routingKey, final Object message, final MessagePostProcessor messagePostProcessor) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceive(routingKey, message, messagePostProcessor);
    }

    public Object convertSendAndReceive(final String exchange, final String routingKey, final Object message, final MessagePostProcessor messagePostProcessor) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceive(exchange, routingKey, message, messagePostProcessor);
    }

    public <T> T convertSendAndReceiveAsType(final Object message, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceiveAsType(message, parameterizedTypeReference);
    }

    public <T> T convertSendAndReceiveAsType(final String routingKey, final Object message, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceiveAsType(routingKey, message, parameterizedTypeReference);
    }

    public <T> T convertSendAndReceiveAsType(final String exchange, final String routingKey, final Object message, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceiveAsType(exchange, routingKey, message, parameterizedTypeReference);
    }

    public <T> T convertSendAndReceiveAsType(final Object message, @Nullable final MessagePostProcessor messagePostProcessor, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceiveAsType(message, messagePostProcessor, parameterizedTypeReference);
    }

    public <T> T convertSendAndReceiveAsType(final String routingKey, final Object message, @Nullable final MessagePostProcessor messagePostProcessor, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceiveAsType(routingKey, message, messagePostProcessor, parameterizedTypeReference);
    }

    public <T> T convertSendAndReceiveAsType(final String exchange, final String routingKey, final Object message, final MessagePostProcessor messagePostProcessor, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplateService.convertSendAndReceiveAsType(exchange, routingKey, message, messagePostProcessor, parameterizedTypeReference);
    }

}
