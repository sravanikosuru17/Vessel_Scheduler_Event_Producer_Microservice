package com.example.vesselSchedulerEventProducer.cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EventProducerDefs extends SpringIntegrationTest{

    @When("^the client calls /api/v1/publishData$")
    public void the_client_calls_api_v1_publish_data() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        String url = "http://localhost:8080/api/v1/publishData";
        executePost(url);

    }

    @Then("^the client receives the status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
       final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

//    @And("the client receives response as {string}")
//    public void the_client_receives_response(String response) throws Throwable {
//        assertThat(latestResponse.getBody(), is(response));
//
//    }
}
