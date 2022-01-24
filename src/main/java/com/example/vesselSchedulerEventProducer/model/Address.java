package com.example.vesselSchedulerEventProducer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class Address {

    @NotBlank
    @Size(min = 3, max = 50)
    private String city;

    @NotBlank
    @Size(min = 3, max = 50)
    private String country;

    @NotBlank
    @Size(min = 3, max = 50)
    private String floor;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    private String postCode;

    @NotBlank
    @Size(min = 3, max = 50)
    private String stateRegion;

    @NotBlank
    @Size(min = 3, max = 50)
    private String street;

    @NotBlank
    @Size(min = 3, max = 50)
    private String streetNumber;


}
