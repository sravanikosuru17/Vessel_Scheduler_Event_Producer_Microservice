package com.example.vesselSchedulerEventProducer.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class CucumberTest extends SpringIntegrationTest{

}
