package com.zhaohy.rabbitmq.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * //TODO
 *
 * @author hongyanzhao2
 * @version 1.0.0
 * @since 2019/12/4 15:20
 */
@Configuration
public class RabbitConfig {
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMandatory(Boolean.TRUE);
        /*
          触发结点
          1.消息推送到server，但是在server里找不到交换机
          2.消息推送到server，找到交换机了，但是没找到队列
          3.消息推送成功
         */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("ConfirmCallback:  相关数据" + correlationData);
            System.out.println("ConfirmCallback:  确认情况" + ack);
            System.out.println("ConfirmCallback:  原因：" + cause);
        });
        /*
           触发点
           1.消息推送到server，找到交换机了，但是没找到队列
         */
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("ReturnCallback:     消息：" + message);
            System.out.println("ReturnCallback:     回应码：" + replyCode);
            System.out.println("ReturnCallback:     回应信息：" + replyText);
            System.out.println("ReturnCallback:     交换机：" + exchange);
            System.out.println("ReturnCallback:     路由键：" + routingKey);
        });
        return rabbitTemplate;
    }
}
