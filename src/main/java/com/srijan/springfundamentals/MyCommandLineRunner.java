package com.srijan.springfundamentals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srijan.springfundamentals.entity.ApplicationUser;
import com.srijan.springfundamentals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {


    }


}
