package com.srijan.springfundamentals.controller;

import com.srijan.springfundamentals.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService welcomeService;

    /**
     * This is a stub API that is
     * used to print hello world
     * @return string called hello world returned
     */
    @GetMapping("/attendance/")
    public String getHello() {
        return "Hello World ";
    }


}
