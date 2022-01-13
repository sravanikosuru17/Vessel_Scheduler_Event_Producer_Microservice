package com.example.vesselSchedulerEventProducer.service;

import com.example.vesselSchedulerEventProducer.model.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	
	@Autowired KafkaTemplate<String, String> kafkaTemplate;

    public static final String KAFKA_TOPIC= "POC.vesselschedule.topic.internal.any.v1";

    //TODO: Kafka integration
    public String publishToTopic(Data data){
    	
    	kafkaTemplate.send(KAFKA_TOPIC, data.toString());

        return null;
    }

}
