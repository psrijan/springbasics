package com.srijan.springfundamentals.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSentenceRequest {
    private Long wordId;
    private String sentence;
}
