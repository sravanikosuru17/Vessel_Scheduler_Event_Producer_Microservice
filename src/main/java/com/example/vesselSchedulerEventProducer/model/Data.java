package com.example.vesselSchedulerEventProducer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {

    private String UNLocationCode;
    private String carrierServiceCode;
    private String carrierVoyageNumber;
    private String delayReasonCode;
    private String eventClassifierCode;
    private String eventDateTime;

    private EventLocation eventLocation;
    private FacilitySMDGCode facilitySMDGCode;
    private String facilityTypeCode;
    private String modeOfTransport;
    private String operationsEventTypeCode;
    private String portCallServiceTypeCode;
    private Publisher publisher;
    private String publisherRole;
    private String remark;
    private TimestampId timestampId;
    private String transportCallSequenceNumber;
    private String vesselIMONumber;
    private VesselPosition vesselPosition;





}
