package com.example.vesselSchedulerEventProducer.cucumber;

import com.example.vesselSchedulerEventProducer.VesselSchedulerEventProducerServiceApplication;
import com.example.vesselSchedulerEventProducer.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(classes = VesselSchedulerEventProducerServiceApplication.class, webEnvironment= WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
    static ResponseResults latestResponse = null;

    @Autowired
    protected RestTemplate restTemplate;



    void executePost(String url) throws IOException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();
        requestCallback.setBody(this.getData());

        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        restTemplate.setErrorHandler(errorHandler);
        latestResponse = restTemplate
                .execute(url , HttpMethod.POST, requestCallback, response -> {
                    if (errorHandler.hadError) {
                        return (errorHandler.getResults());
                    } else {
                        return (new ResponseResults(response));
                    }
                });
    }
    private class ResponseResultErrorHandler implements ResponseErrorHandler {
        private ResponseResults results = null;
        private Boolean hadError = false;

        private ResponseResults getResults() {
            return results;
        }

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            hadError = response.getRawStatusCode() >= 400;
            return hadError;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            results = new ResponseResults(response);
        }
    }
    public String mapToJson(final Data object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    public String getData() throws JsonProcessingException {
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
        return this.mapToJson(data);
    }

}
