package com.srijan.springfundamentals.dto.response;

import com.srijan.springfundamentals.dto.server.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordSetDetail extends ModelBase {
    public Long id;
    public String name;
    public String code;
    public String type;
}
