package com.example.vesselSchedulerEventProducer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Publisher {
    private Address address;
    private List<Object> identifyingCodes;

    @NotBlank
    @Size(min = 3, max = 50)
    private String nmftaCode;

    @NotBlank
    @Size(min = 3, max = 50)
    private String partyName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String publicKey;

    @NotBlank
    @Size(min = 3, max = 50)
    private String taxReference1;

    @NotBlank
    @Size(min = 3, max = 50)
    private String taxReference2;
}
