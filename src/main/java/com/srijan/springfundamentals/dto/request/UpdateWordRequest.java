package com.srijan.springfundamentals.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateWordRequest {
    private Long id;
    private String name;
    private String definition;
}
