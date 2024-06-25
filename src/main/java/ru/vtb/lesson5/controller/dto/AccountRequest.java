package ru.vtb.lesson5.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    @NotNull(message = "Идентификатор экземпляра продукта (instanceId) не должен быть пустым.")
    @Min(value = 0, message = "Идентификатор экземпляра продукта (instanceId) должен быть положительным целым.")
    private Long   instanceId;
    private String registryTypeCode;
    private String accountType;
    @Size(max = 3, message = "Код валюты (currencyCode) должен быть не длиннее {max} символов.")
    private String currencyCode;
    private String branchCode;
    @Pattern(regexp = "00", message = "Код срочности (priorityCode) должен быть всегда \"00\" для ПП РО ЮЛ.")
    private String priorityCode;
    private String mdmCode;
    private String clientCode;
    private String trainRegion;
    private String counter;
    private String salesCode;
}
