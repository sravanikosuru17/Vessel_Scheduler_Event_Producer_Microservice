package com.example.vesselSchedulerEventProducer.controller;

import com.example.vesselSchedulerEventProducer.exception.BadRequestException;
import com.example.vesselSchedulerEventProducer.exception.ServiceException;
import com.example.vesselSchedulerEventProducer.model.Data;
import com.example.vesselSchedulerEventProducer.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1")
public class vesselSchedulerEventProducerController {

    @Autowired
    Producer producer;

    @PostMapping(path="/publishData", consumes = "application/json", produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String publishDetails(@RequestBody Data data) throws ServiceException, BadRequestException {
        return producer.publishToTopic(data);
    }
}
