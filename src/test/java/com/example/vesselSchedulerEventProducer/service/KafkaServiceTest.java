package com.example.vesselSchedulerEventProducer.service;

import com.example.vesselSchedulerEventProducer.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
//@Import(KafkaService.class)
@ContextConfiguration(classes = {KafkaTemplate.class})
class KafkaServiceTest {


    KafkaService kafkaService;

    @MockBean
    KafkaTemplate kafkaTemplate;

    @BeforeEach
    void setUp(){
        this.kafkaService = new KafkaService(kafkaTemplate,"VesselSchedulEvent","POC.vesselschedule.topic.internal.any.v1");
    }

    @Test
    public void  testPublishToTopic() {
        Data data = new Data("USNYC", "FE1", "2103S", "WEA", "ACT", "2021-12-13T05:36:44.850Z",
                new EventLocation("USNYC",
                        new Address("K�benhavn", "Denmark", "5. sal", "Henrik", "1306", "N/A", "Kronprincessegade", "54"),
                        "48.8585500", "Eiffel Tower", "2.294492036"),
                new FacilitySMDGCode(), "BRTH", "BARGE", "ARRI", "BUNK",
                new Publisher(
                        new Address("K�benhavn", "Denmark", "5. sal", "Henrik", "1306", "N/A", "Kronprincessegade", "54"),
                        new ArrayList<>(), "string", "Asseco Denmark", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IkFzaW",
                        "CVR-25645774", "CVR-25645774"),
                "AG", "Port closed due to strike", new TimestampId("0"), "2", "9321483",
                new VesselPosition("48.8585500", "2.294492036"));

        String received = kafkaService.publishToTopic(data);
        assertThat(received).isNotNull();

    }

}