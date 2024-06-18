package ru.vtb.lesson5.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CoefficientDirectionEnum {
    @JsonProperty("повышающий")
    INCREASE("повышающий"),
    @JsonProperty("понижающий")
    DECREASE("понижающий");

    private final String name;
}
