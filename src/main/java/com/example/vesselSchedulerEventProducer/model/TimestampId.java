package com.example.vesselSchedulerEventProducer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimestampId {

    @NotBlank
    @Size(min = 3, max = 50)
    private String timestamp;

}
