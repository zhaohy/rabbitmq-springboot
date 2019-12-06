package com.zhaohy.rabbitmq.config;

import com.zhaohy.rabbitmq.listener.DirectReceiverListener;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * //TODO
 *
 * @author hongyanzhao2
 * @version 1.0.0
 * @since 2019/12/4 15:45
 */
@Configuration
public class MessageListenerConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private DirectReceiverListener directReceiverListener;
    @Autowired
    private DirectRabbitConfig directRabbitConfig;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        //rabbitMQ默认为自动确认，将其改为手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueues(directRabbitConfig.directQueue());
        container.setMessageListener(directReceiverListener);
        return container;
    }
}
