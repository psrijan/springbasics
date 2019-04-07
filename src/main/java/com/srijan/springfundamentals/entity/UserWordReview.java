package com.srijan.springfundamentals.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWordReview {

    private Long id;

    //one to one
    private ApplicationUser applicationUser;

    //one to one
    private Word word;

    //one to one
    private WordGroup wordGroup;
}
