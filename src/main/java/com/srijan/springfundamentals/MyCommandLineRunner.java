package com.srijan.springfundamentals;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.debug("Debug Log Enabled");
        log.info("Info Log Enabled");
        log.error("Error Log Enabled");
    }
}
