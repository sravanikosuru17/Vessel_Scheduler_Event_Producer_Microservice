package com.example.vesselSchedulerEventProducer.controller;

import com.example.vesselSchedulerEventProducer.model.Data;
import com.example.vesselSchedulerEventProducer.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/")
public class vesselSchedulerEventProducerController {

    @Autowired
    Producer producer;
    
	
	@Autowired KafkaTemplate<String, String> kafkaTemplate;
	 
    
    public static final String TOPIC="POC.vesselschedule.topic.internal.any.v1";

    @PostMapping(path="/publishData", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> publishDetails(@RequestBody Data data){
    	
    	kafkaTemplate.send(TOPIC, data.toString());
    	
        String eventId = producer.publishToTopic(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventId);
    }
}
