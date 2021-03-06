package com.iquantex.email.service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iquantex.email.common.config.MQConstants;
import com.iquantex.event.email.EmailEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 监听Kafka Topic,从里面取数据
 **/
@Service
@Slf4j
public class YbKafkaConsumer {


    @Autowired
    private SendMessageService sendMessageService;
    private static Gson gson = new GsonBuilder().create();

    /**
     * 监听一个Kafka 主题
     **/
    @KafkaListener(topics = {"#{'${spring.kafka.consumer.topic}'.split(',')}"})
    public void receiveMessageFromKafka(ConsumerRecord<?, ?> record, Acknowledgment ack) {
        log.info("监听消息,MailUid:{}", JSONObject.parseObject(String.valueOf(record.value()), EmailEvent.class).getMailId());

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            sendMessageService.sendMessages(JSONObject.parseObject(String.valueOf(record.value()), EmailEvent.class));
        }
        ack.acknowledge();
    }


}