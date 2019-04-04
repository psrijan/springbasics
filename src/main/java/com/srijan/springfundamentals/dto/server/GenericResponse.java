package com.srijan.springfundamentals.dto.server;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class GenericResponse extends ModelBase {
    public boolean success;
    public String message;
}
