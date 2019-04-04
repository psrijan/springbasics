package com.srijan.springfundamentals.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetail {

    private String name;

    public UserDetail(String name) {
        this.name = name;
    }

    public UserDetail() {

    }
}
