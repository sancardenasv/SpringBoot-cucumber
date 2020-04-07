package com.electroeing.cucumber.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MainController {
    private static final Logger log = LogManager.getLogger(MainController.class);

    @GetMapping(value = "/version")
    public String getVersion() {
        log.info("Requested application version");
        return "1.0";
    }

    @GetMapping(value = "/greet")
    public String greet(@RequestParam String username, HttpServletResponse response) {
        if (username.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "username required";
        }
        String greet = "Hello ".concat(username).concat("!");
        log.info("Requested greeting: {}", greet);
        return greet;
    }
}
