package com.srijan.springfundamentals.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

@Slf4j
@ApplicationScope
@RestController
@RequestMapping("/hamro")
public class TestController {

    private int val=1;

    @GetMapping( produces = "text/html")
    public String returnValue() {
        log.debug("Incoming request for test controller...");
        val++;
        if(val%2 ==0)
            return "ID 12345123123";
        return "Failed";
    }

}
