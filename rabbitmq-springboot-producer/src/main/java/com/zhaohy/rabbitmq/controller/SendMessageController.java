package com.zhaohy.rabbitmq.controller;

import com.zhaohy.rabbitmq.constant.RabbitConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * //TODO
 *
 * @author hongyanzhao2
 * @version 1.0.0
 * @since 2019/12/4 11:04
 */
@RestController
@RequestMapping("/send")
public class SendMessageController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/directMessage")
    public String directMessage(){
        String messageData = "test message，hello";
        Map<String, Object> map = getDate(messageData);
        rabbitTemplate.convertAndSend(RabbitConstant.TEST_DIRECT_EXCHANGE, RabbitConstant.TEST_DIRECT_ROUTING, map);
        return "OK";
    }

    @GetMapping("/topicMessageMan")
    public String topicMessageMan(){
        String messageData = "test message, hello man";
        Map<String, Object> map = getDate(messageData);
        rabbitTemplate.convertAndSend(RabbitConstant.TOPIC_EXCHANGE, RabbitConstant.MAN, map);
        return "OK";
    }

    @GetMapping("/topicMessageTotal")
    public String topicMessageTotal(){
        String messageData = "test message, woman is all";
        Map<String, Object> map = getDate(messageData);
        rabbitTemplate.convertAndSend(RabbitConstant.TOPIC_EXCHANGE, RabbitConstant.WOMAN, map);
        return "OK";
    }

    @GetMapping("/fanoutMessage")
    public String fanoutMessage(){
        String messageData = "test message, fanout message";
        Map<String, Object> map = getDate(messageData);
        rabbitTemplate.convertAndSend(RabbitConstant.FANOUT_EXCHANGE, null, map);
        return "OK";
    }

    private Map<String, Object> getDate(String messageData) {
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String messageId = String.valueOf(UUID.randomUUID());
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        return map;
    }

    @GetMapping("/testMessageAck")
    public String testMessageAck(){
        String messageData = "message : non-existent-exchange test message";
        Map<String, Object> map = getDate(messageData);
        rabbitTemplate.convertAndSend("non-existent-exchange", "TEST_DIRECT_ROUTING", map);
        return "OK";
    }

    @GetMapping("/testMessageAck2")
    public String testMessageAck2(){
        String messageData = "message : LONELY_DIRECT_EXCHANGE test message";
        Map<String, Object> map = getDate(messageData);
        rabbitTemplate.convertAndSend(RabbitConstant.LONELY_DIRECT_EXCHANGE, "TEST_DIRECT_ROUTING", map);
        return "OK";
    }

}
