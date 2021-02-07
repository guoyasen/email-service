package com.iquantex.email.common.config;

import org.springframework.beans.factory.annotation.Value;

public class MQConstants {
    public static final class Topic{
        /**
         * 邮件交换机
         */
        @Value("${spring.kafka.consumer.topic}")
        public static final String ITEM_EXCHANGE_NAME = "boi_inst_instruction_email";
    }

}
