package com.example.vesselSchedulerEventProducer.service;

import com.example.vesselSchedulerEventProducer.model.Data;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    public static final String topic= "POC.vesselschedule.topic.internal.any.v1";

    //TODO: Kafka integration
    public String publishToTopic(Data data){

        return null;
    }

}
