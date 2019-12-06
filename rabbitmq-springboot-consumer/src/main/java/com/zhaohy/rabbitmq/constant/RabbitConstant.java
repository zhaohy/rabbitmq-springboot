package com.zhaohy.rabbitmq.constant;

/**
 * //TODO
 *
 * @author hongyanzhao2
 * @version 1.0.0
 * @since 2019/12/4 11:11
 */
public class RabbitConstant {
    /**
     * 直连型交换机
     */
    public static final String TEST_DIRECT_QUEUE = "TEST_DIRECT_QUEUE";
    public static final String TEST_DIRECT_EXCHANGE = "TEST_DIRECT_EXCHANGE";
    public static final String TEST_DIRECT_ROUTING = "TEST_DIRECT_ROUTING";
    /**
     * 主题交换机
     */
    public static final String MAN = "TOPIC.MAN";
    public static final String WOMAN = "TOPIC.WOMAN";
    public static final String TOPIC_EXCHANGE = "TOPIC_EXCHANGE";
    /**
     * 扇型交换机
     */
    public static final String FANOUT_A = "FANOUT.A";
    public static final String FANOUT_B = "FANOUT.B";
    public static final String FANOUT_C = "FANOUT.C";
    public static final String FANOUT_EXCHANGE = "FANOUT_EXCHANGE";
}
