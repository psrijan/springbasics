package com.srijan.springfundamentals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srijan.springfundamentals.entity.ApplicationUser;
import com.srijan.springfundamentals.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        List<ApplicationUser> applicationUser = userService.listUserData();
        log.debug("application user : {}", applicationUser);
    }


}
