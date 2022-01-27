package com.example.vesselSchedulerEventProducer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class Data {

    @NotBlank(message = "UNLocationCode is mandatory")
    @Size(min = 3, max = 50, message = "UNLocationCode should be between 3 & 50 characters")
    private String UNLocationCode;

    @NotBlank(message = "carrierServiceCode is mandatory")
    @Size(min = 3, max = 50, message = "carrierServiceCode should be between 3 & 50 characters")
    private String carrierServiceCode;

    @NotBlank
    @Size(min = 3, max = 50)
    private String carrierVoyageNumber;

    @NotBlank
    @Size(min = 3, max = 50)
    private String delayReasonCode;

    @NotBlank
    @Size(min = 3, max = 50)
    private String eventClassifierCode;

    @NotBlank
    @Size(min = 3, max = 50)
    private String eventDateTime;

    private EventLocation eventLocation;
    private FacilitySMDGCode facilitySMDGCode;

    @NotBlank
    @Size(min = 3, max = 50)
    private String facilityTypeCode;

    @NotBlank
    @Size(min = 3, max = 50)
    private String modeOfTransport;

    @NotBlank
    @Size(min = 3, max = 50)
    private String operationsEventTypeCode;

    @NotBlank
    @Size(min = 3, max = 50)
    private String portCallServiceTypeCode;

    private Publisher publisher;

    @NotBlank
    @Size(min = 1, max = 50)
    private String publisherRole;

    @NotBlank
    @Size(min = 3, max = 50)
    private String remark;
    private TimestampId timestampId;

    @NotBlank
    @Size(min = 1, max = 50)
    private String transportCallSequenceNumber;

    @NotBlank
    @Size(min = 3, max = 50)
    private String vesselIMONumber;

    private VesselPosition vesselPosition;





}
