package com.srijan.springfundamentals.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSentenceRequest {
    @JsonProperty(value = "sentenceId")
    private Long id;
    @JsonProperty(value = "sentence")
    private String value;
}
