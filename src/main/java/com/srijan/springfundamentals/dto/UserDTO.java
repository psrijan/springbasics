package com.srijan.springfundamentals.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String name;

    public UserDTO(String name) {
        this.name = name;
    }

    public UserDTO() {

    }
}
