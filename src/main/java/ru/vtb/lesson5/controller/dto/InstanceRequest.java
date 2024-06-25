package ru.vtb.lesson5.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vtb.lesson5.model.ProductTypeEnum;
import ru.vtb.lesson5.model.RateTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class InstanceRequest {
    private Integer         instanceId;
    @NotNull(message = "Тип экземпляра продукта (productType) не должен быть пустым.")
    private ProductTypeEnum productType;
    @NotBlank(message = "Код продукта в каталоге продуктов (productCode) не должен быть пустым.")
    private String          productCode;
    @NotBlank(message = "Тип регистра (registerType) не должен быть пустым.")
    private String          registerType;
    @NotBlank(message = "Код клиента (mdmCode) не должен быть пустым.")
    private String          mdmCode;
    @NotBlank(message = "Номер договора (contractNumber) не должен быть пустым.")
    private String          contractNumber;
    @NotNull(message = "Дата заключения договора обслуживания (contractDate) не должна быть пустой.")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate       contractDate;
    @NotNull(message = "Приоритет (priority) не должен быть пустым.")
    private Integer         priority;
    private BigDecimal      interestRatePenalty;
    private BigDecimal      minimalBalance;
    private BigDecimal      thresholdAmount ;
    private String          accountingDetails;
    private RateTypeEnum    rateType;
    private BigDecimal      taxPercentageRate ;
    private BigDecimal      technicalOverdraftLimitAmount;
    @NotNull(message = "ID договора (contractId) не должен быть пустым.")
    private Integer         contractId;
    @NotBlank(message = "Код филиала (branchCode) не должен быть пустым.")
    private String          branchCode;
    @NotBlank(message = "Код валюты (isoCurrencyCode) не должен быть пустым.")
    private String          isoCurrencyCode;
    @NotBlank(message = "Код срочности договора (urgencyCode) не должен быть пустым.")
    private String          urgencyCode;
    private Integer         referenceCode;
    private AdditionalPropertiesVip additionalPropertiesVip;
    @JsonProperty("instanceArrangement")
    private @Valid List<InstanceArrangementDto> instanceArrangements;

    @Getter
    @Setter
    @NoArgsConstructor
    public class AdditionalPropertiesVip {
        List<Map<String, String>> data;
    }
}
