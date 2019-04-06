package com.srijan.springfundamentals.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class ApiError {
    @NotNull private List<String> errorMessages;
}
