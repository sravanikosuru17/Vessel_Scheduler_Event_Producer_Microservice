package com.example.vesselSchedulerEventProducer.exception;

public class BadRequestException extends Exception {

    public BadRequestException(){
        super();
    }

    public BadRequestException(String message){
        super(message);
    }
}
