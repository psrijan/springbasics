package com.srijan.springfundamentals.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateWordRequest {
    private String name;
    private String definition;
}
