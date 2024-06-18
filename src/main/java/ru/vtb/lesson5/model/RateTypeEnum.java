package ru.vtb.lesson5.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RateTypeEnum {
    @JsonProperty("дифференцированная")
    DIFFERENTIAL("дифференцированная"),
    @JsonProperty("прогрессивная")
    PROGRESSIVE("прогрессивная");

    private final String name;
}
