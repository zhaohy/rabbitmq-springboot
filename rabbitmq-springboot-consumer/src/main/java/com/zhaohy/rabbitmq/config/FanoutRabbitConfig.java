package com.zhaohy.rabbitmq.config;

import com.zhaohy.rabbitmq.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 扇型交换机，这个交换机没有路由键概念，就算你绑了路由键也是无视的。
 * 这个交换机在接收到消息后，会直接转发到绑定到它上面的所有队列。
 *
 * @author hongyanzhao2
 * @version 1.0.0
 * @since 2019/12/4 14:35
 */
@Configuration
public class FanoutRabbitConfig {
    @Bean
    public Queue queueA(){
        return new Queue(RabbitConstant.FANOUT_A);
    }
    @Bean
    public Queue queueB(){
        return new Queue(RabbitConstant.FANOUT_B);
    }
    @Bean
    public Queue queueC(){
        return new Queue(RabbitConstant.FANOUT_C);
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(RabbitConstant.FANOUT_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeA(){
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }
    @Bean
    Binding bindingExchangeB(){
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }
    @Bean
    Binding bindingExchangeC(){
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }

}
