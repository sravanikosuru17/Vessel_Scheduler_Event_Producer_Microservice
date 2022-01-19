package com.example.vesselSchedulerEventProducer.service;

import com.example.vesselSchedulerEventProducer.exception.BadRequestException;
import com.example.vesselSchedulerEventProducer.exception.ServiceException;
import com.example.vesselSchedulerEventProducer.model.Data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.producer.topic}")
    private String kafkaProducerTopic;

    @Value("${kafka.producer.message.key}")
    private String kafkaProducerMessageKey;

    public String publishToTopic(Data data) throws BadRequestException, ServiceException {

        try {
            Message<String> message = MessageBuilder
                    .withPayload(data.toString())
                    .setHeader(KafkaHeaders.TOPIC, kafkaProducerTopic)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, kafkaProducerMessageKey)
                    .setHeader("Event_ID", "1234")
                    .build();

            log.info("sending message='{}' to topic='{}'", data, kafkaProducerTopic);
            String eventId = String.valueOf(kafkaTemplate.send(message));
            log.info("Event id after sending message ='{}'", eventId);
            if (eventId.isBlank()) {
                throw new ServiceException("Event Id is null");
            }
            return eventId;

        } catch (RuntimeException ex) {
            throw new BadRequestException("Error Occurred while calling Service");
        }
    }
}