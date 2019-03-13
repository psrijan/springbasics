package com.srijan.springfundamentals.properties.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "user")
public class MainUser {

    private String username;
    private String password;
    private String url;
}
