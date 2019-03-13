package com.srijan.springfundamentals.service;

import org.springframework.stereotype.Component;

@Component
public class WelcomeService {

    private String val;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
