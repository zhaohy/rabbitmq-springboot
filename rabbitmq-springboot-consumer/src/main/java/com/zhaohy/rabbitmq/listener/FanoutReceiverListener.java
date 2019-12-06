package com.zhaohy.rabbitmq.listener;

import com.zhaohy.rabbitmq.constant.RabbitConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 监听扇形交换机
 *
 * @author hongyanzhao2
 * @version 1.0.0
 * @since 2019/12/4 14:43
 */
@Component
public class FanoutReceiverListener {
    @RabbitListener(queues = RabbitConstant.FANOUT_A)
    public void processA(Map message){
        System.out.println("processA receiver message : " + message.toString());
    }
    @RabbitListener(queues = RabbitConstant.FANOUT_B)
    public void processB(Map message){
        System.out.println("processB receiver message : " + message.toString());
    }
    @RabbitListener(queues = RabbitConstant.FANOUT_C)
    public void processC(Map message){
        System.out.println("processC receiver message : " + message.toString());
    }
}
