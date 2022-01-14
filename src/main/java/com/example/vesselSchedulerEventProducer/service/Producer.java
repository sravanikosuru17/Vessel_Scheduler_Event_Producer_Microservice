package com.example.vesselSchedulerEventProducer.service;

import com.example.vesselSchedulerEventProducer.exception.BadRequestException;
import com.example.vesselSchedulerEventProducer.exception.ServiceException;
import com.example.vesselSchedulerEventProducer.model.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	
	@Autowired KafkaTemplate<String, String> kafkaTemplate;

    public static final String KAFKA_TOPIC= "POC.vesselschedule.topic.internal.any.v1";

    public String publishToTopic(Data data) throws ServiceException, BadRequestException {
    	try {
            String eventId = "";
                    //String.valueOf(kafkaTemplate.send(KAFKA_TOPIC, data.toString()));
            if(eventId.isBlank()){
                throw new ServiceException("Event Id is null");
            }
            return eventId;

        }catch (RuntimeException ex){
            throw new BadRequestException("Error Occurred while calling Service");
        }
    }

}
