package com.zhaohy.rabbitmq.listener;

import com.zhaohy.rabbitmq.constant.RabbitConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 监听主题交换机
 *
 * @author hongyanzhao2
 * @version 1.0.0
 * @since 2019/12/4 14:00
 */
@Component
public class TopicReceiverListener {
    @RabbitListener(queues = RabbitConstant.MAN)
    public void processMan(Map message){
        System.out.println("Topic Receiver man message : " + message.toString());
    }

    @RabbitListener(queues = RabbitConstant.WOMAN)
    public void processTopicTotal(Map message){
        System.out.println("Topic Receiver TopicTotal message : " + message.toString());
    }
}
