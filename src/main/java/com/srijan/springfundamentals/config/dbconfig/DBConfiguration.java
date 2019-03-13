package com.srijan.springfundamentals.config.dbconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@ConfigurationProperties("spring.datasource")
@SuppressWarnings("unused")
public class DBConfiguration {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
        log.info("DB Connection for DEV - H2");
        log.info(driverClassName);
        log.info(url);
        return "DB connection for DEV - H2";
    }

    @Profile("test")
    @Bean
    public String testDatabaseConnection() {
        log.info("DB Connection for Test");
        log.info(driverClassName);
        log.info(url);
        return "DB Connection for test";
    }

    @Profile("prod")
    @Bean
    public String prodDatabaseConnection() {
        log.info("DB Connection for Prod");
        log.info(driverClassName);
        log.info(url);
        return "Prod DB Connection";
    }
}
