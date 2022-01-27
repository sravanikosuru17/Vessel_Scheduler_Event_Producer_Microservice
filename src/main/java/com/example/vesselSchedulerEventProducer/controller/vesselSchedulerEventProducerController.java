package com.example.vesselSchedulerEventProducer.controller;

import com.example.vesselSchedulerEventProducer.exception.BadRequestException;
import com.example.vesselSchedulerEventProducer.exception.ServiceException;
import com.example.vesselSchedulerEventProducer.model.Data;
import com.example.vesselSchedulerEventProducer.service.KafkaService;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;


@RestController
@RequestMapping(path="/api/v1")
public class vesselSchedulerEventProducerController {

    @Autowired
    KafkaService kafkaService;

    @ApiOperation("API to Publish data on Kafka")
    @PostMapping(path="/publishData", consumes = "application/json", produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String publishDetails(@Valid @RequestBody Data data, BindingResult bindingResult) throws BadRequestException {
		String errString = "";
    	if (bindingResult.hasErrors()) {
			for (ObjectError e: bindingResult.getAllErrors()) {
				errString+=e.getDefaultMessage() + "\r\n";
			}
			throw new BadRequestException(errString);
		}
        return kafkaService.publishToTopic(data);
    }
}
