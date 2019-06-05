package com.srijan.springfundamentals.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping
    public String displayMessage() {
        return "<h1>Topup Admin</h1>";
    }

    @PostMapping(path = "/test" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public String testMe() {
        return "asd";
    }

}
