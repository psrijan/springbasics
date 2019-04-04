package com.srijan.springfundamentals.dto.server;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerResponse {

    private String message;
    private boolean success;
}
