package com.example.vesselSchedulerEventProducer.controller;

import com.example.vesselSchedulerEventProducer.model.*;
import com.example.vesselSchedulerEventProducer.service.KafkaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;



@ExtendWith(SpringExtension.class)
@WebMvcTest(value = vesselSchedulerEventProducerController.class)
@Import(KafkaService.class)
@ComponentScan("com.example.vesselSchedulerEventProducerController")
class vesselSchedulerEventProducerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    KafkaService kafkaService;

    @DisplayName("Method to test publish details")
    @Test
    void testPublishDetails() throws Exception {
        String inputJson = this.getData();
        String URI = "/api/v1/publishData";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

    }

    @DisplayName("Method to test not found exception")
    @Test
    void test_NotFoundException() throws Exception {
        String data = this.getData();
        String URI = "/api/v1/publishData1";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(data)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

    }

    @DisplayName("Method to test Bad Request Exception")
    @Test
    void test_BadRequestException() throws Exception {
        String data = "123";
        String URI = "/api/v1/publishData";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(data)
                .contentType(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

    }


    public String mapToJson(final Data object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    public String getData() throws JsonProcessingException {
        Data data = new Data("USNYC", "FE1", "2103S", "WEA", "ACT", "2021-12-13T05:36:44.850Z",
                new EventLocation("USNYC",
                        new Address("K???benhavn", "Denmark", "5. sal", "Henrik", "1306", "N/A", "Kronprincessegade", "54"),
                        "48.8585500", "Eiffel Tower", "2.294492036"),
                new FacilitySMDGCode(), "BRTH", "BARGE", "ARRI", "BUNK",
                new Publisher(
                        new Address("K???benhavn", "Denmark", "5. sal", "Henrik", "1306", "N/A", "Kronprincessegade", "54"),
                        new ArrayList<>(), "string", "Asseco Denmark", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IkFzaW",
                        "CVR-25645774", "CVR-25645774"),
                "AG", "Port closed due to strike", new TimestampId("0"), "2", "9321483",
                new VesselPosition("48.8585500", "2.294492036"));
        return this.mapToJson(data);
    }



}

