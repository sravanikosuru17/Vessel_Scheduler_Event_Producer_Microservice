package com.example.vesselSchedulerEventProducer.service;

import com.example.vesselSchedulerEventProducer.exception.ServiceException;
import com.example.vesselSchedulerEventProducer.model.Data;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.config.ConfigException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class KafkaService {
	

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.producer.topic}")
    private String kafkaProducerTopic;

    @Value("${kafka.producer.message.key}")
    private String kafkaProducerMessageKey;


    public String publishToTopic(Data data) {


        try {
            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            Message<String> message = MessageBuilder
                    .withPayload(data.toString())
                    .setHeader(KafkaHeaders.TOPIC, kafkaProducerTopic)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, kafkaProducerMessageKey)
                    .setHeader("Event_ID", uuidAsString)
                    .build();

            log.info("sending message='{}' to topic='{}'", message, kafkaProducerTopic);
            return String.valueOf(kafkaTemplate.send(message));

        } catch (NullPointerException ex) {
            log.error("Null Exception :" + ex);
            ex.printStackTrace();
            throw new NullPointerException(ex.getMessage());
        } catch (ConfigException ex) {
            log.error("Config Exception :" + ex);
            ex.printStackTrace();
            throw new ConfigException(ex.getMessage());
        } catch (KafkaException ex) {
            log.error("Kafka Exception :" + ex);
            ex.printStackTrace();
            throw new KafkaException(ex.getMessage());
        }

    }
}
