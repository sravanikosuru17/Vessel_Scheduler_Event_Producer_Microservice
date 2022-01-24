package com.example.vesselSchedulerEventProducer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class VesselPosition {

    @NotBlank
    @Size(min = 3, max = 50)
    private String latitude;

    @NotBlank
    @Size(min = 3, max = 50)
    private String longitude;

}
