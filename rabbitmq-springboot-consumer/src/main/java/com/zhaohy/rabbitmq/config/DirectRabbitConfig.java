package com.zhaohy.rabbitmq.config;

import com.zhaohy.rabbitmq.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 直连型交换机，根据消息携带的路由键将消息投递给对应队列
 * 大致流程，有一个队列绑定到一个直连交换机上，同时赋予一个路由键 routing key 。
 * 然后当一个消息携带着路由值为X，这个消息通过生产者发送给交换机时，交换机就会根据这个路由值X去寻找绑定值也是X的队列。
 * @author hongyanzhao2
 * @version 1.0.0
 * @since 2019/12/4 10:58
 */
@Configuration
public class DirectRabbitConfig {
    /**
     * 队列起名：TestDirectQueue
     * @return 返回队列信息
     */
    @Bean
    public Queue directQueue(){
        return new Queue(RabbitConstant.TEST_DIRECT_QUEUE, true);
    }

    /**
     * 交换机名：TestDirectExchange
     * @return 返回交换机信息
     */
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(RabbitConstant.TEST_DIRECT_EXCHANGE);
    }

    /**
     * 绑定，将交换机与队列绑定，并设置用于匹配键 TestDirectRouting
     * @return 绑定结果
     */
    @Bean
    Binding bindingDirect(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(RabbitConstant.TEST_DIRECT_ROUTING);
    }
}
