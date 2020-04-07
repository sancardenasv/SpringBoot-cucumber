package com.electroeing.cucumber.steps;

import com.electroeing.cucumber.CucumberApplication;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {CucumberApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class MainControllerSteps {
    private static final Logger log = LogManager.getLogger(MainControllerSteps.class);

    private int port = 8080;
    private RestTemplate restTemplate = new RestTemplate();
    private String baseUrl = "http://localhost:".concat(String.valueOf(port));

    private ResponseEntity<String> response;

    @When("^the client calls \\/version$")
    public void theClientCallsVersion() {
        response = restTemplate.getForEntity(baseUrl.concat("/version"), String.class);
        log.info("Get result: {}", response);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void theClientReceivesStatusCodeOf(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCodeValue());
    }

    @And("^the client receives server version (.+)$")
    public void theClientReceivesServerVersion(String version) {
        Assert.assertEquals(version, response.getBody());
    }
}
