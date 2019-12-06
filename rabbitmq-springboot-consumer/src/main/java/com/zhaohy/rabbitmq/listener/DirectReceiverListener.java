package com.zhaohy.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import com.zhaohy.rabbitmq.constant.RabbitConstant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 监听直连交换机
 *
 * @author hongyanzhao2
 * @version 1.0.0
 * @since 2019/12/4 11:26
 */
@Component
@RabbitListener(queues = RabbitConstant.TEST_DIRECT_QUEUE)
public class DirectReceiverListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            String msg = message.toString();
            String[] msgArr = msg.split("'");
            Map<String, String> msgMap = mapStringToMap(msgArr[1].trim());
            String messageId = msgMap.get("messageId");
            String messageData = msgMap.get("messageData");
            String createTime = msgMap.get("createTime");
            System.out.printf("messageId:%s  messageData:%s  createTime:%s%n", messageId, messageData, createTime);
            channel.basicAck(deliveryTag, Boolean.FALSE);
        } catch (IOException e) {
            channel.basicAck(deliveryTag, Boolean.FALSE);
            e.printStackTrace();
        }
    }

    private Map<String, String> mapStringToMap(String str){
        str = str.substring(1, str.length()-1);
        String[] strs = str.split(",");
        Map<String, String> map = new HashMap<>();
        for (String s : strs) {
            String[] split = s.split("=");
            if(split.length<2){
                continue;
            }
            String key = split[0].trim();
            String value = split[1];
            map.put(key, value);
        }
        return map;
    }
}
