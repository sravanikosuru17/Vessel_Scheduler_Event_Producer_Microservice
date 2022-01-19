package com.example.vesselSchedulerEventProducer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ServiceException.class)
    public @ResponseBody ErrorResponse handleServiceExceptions(ServiceException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND);
        error.setMessage(ex.getMessage());
        return error;
    }

    @ExceptionHandler(BadRequestException.class)
    public @ResponseBody ErrorResponse handleBadRequestException(BadRequestException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(ex.getMessage());
        return error;
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody ErrorResponse handleUnexpectedExceptions(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setMessage(ex.getMessage());
        return error;
    }
}
