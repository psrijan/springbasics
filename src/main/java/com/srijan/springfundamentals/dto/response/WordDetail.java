package com.srijan.springfundamentals.dto.response;

import com.srijan.springfundamentals.dto.server.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordDetail extends ModelBase {
    private Long id;

    private String name;

    private String definition;
}
