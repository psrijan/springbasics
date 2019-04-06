package com.srijan.springfundamentals.dto.server;

import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
public class GenericResponse extends ModelBase {
    public boolean success;
    public String message;
}
