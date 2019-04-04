package com.srijan.springfundamentals.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.srijan.springfundamentals.dto.server.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SentenceDetail extends ModelBase {
    private Long id;
    @JsonProperty(value = "sentence")
    private String value;
}
