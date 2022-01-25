package com.example.vesselSchedulerEventProducer.controller;

import com.example.vesselSchedulerEventProducer.model.Data;
import com.example.vesselSchedulerEventProducer.service.KafkaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/api/v1")
public class vesselSchedulerEventProducerController {

    @Autowired
    KafkaService kafkaService;

    @ApiOperation("API to Publish data on Kafka")
    @PostMapping(path="/publishData", consumes = "application/json", produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String publishDetails(@RequestBody Data data)  {
        return kafkaService.publishToTopic(data);
    }
}
