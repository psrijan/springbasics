package com.srijan.springfundamentals.dto.server;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.srijan.springfundamentals.entity.ApplicationUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordReviewRequest extends ModelBase{

    @JsonIgnore
    private ApplicationUser applicationUser;

    private Long userId;
    private Long wordId;
    private Long correctCount;
    private Long incorrectCount;
    private Long consecutiveCorrectCount;
}

