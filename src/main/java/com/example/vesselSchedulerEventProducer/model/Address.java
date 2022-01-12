package com.example.vesselSchedulerEventProducer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    //@Size(min = 10, max = 10)
    private String city;
    private String country;
    private String floor;
    private String name;
    private String postCode;
    private String stateRegion;
    private String street;
    private String streetNumber;


}
