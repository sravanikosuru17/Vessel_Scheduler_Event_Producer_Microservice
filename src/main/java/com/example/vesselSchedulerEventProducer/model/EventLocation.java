package com.example.vesselSchedulerEventProducer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventLocation {

    private String UNLocationCode;
    private Address address;
    private String latitude;
    private String locationName;
    private String longitude;


}
