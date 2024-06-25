package ru.vtb.lesson5.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductTypeEnum {
    @JsonProperty("НСО")
    NSO("НСО"),
    @JsonProperty("СМО")
    SMO("СМО"),
    @JsonProperty("ЕЖО")
    EZHO("ЕЖО"),
    @JsonProperty("ДБДС")
    DBDS("ДБДС"),
    @JsonProperty("договор")
    DOG("договор");

    private final String name;
}
