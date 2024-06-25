package ru.vtb.lesson5.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vtb.lesson5.model.ProductTypeEnum;
import ru.vtb.lesson5.model.CoefficientDirectionEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class InstanceArrangementDto {
    private String          generalAgreementId;
    private String          supplementaryAgreementId;
    private ProductTypeEnum arrangementType;
    private Integer         shedulerJobId;
    @NotBlank(message = "Номер ДС (instanceArrangement.number) не должен быть пустым.")
    private String          number;
    @NotNull(message = "Дата начала сделки (instanceArrangement.openingDate) не должна быть пустой.")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate       openingDate;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate       closingDate;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate       cancelDate;
    private Integer         validityDuration;
    private String          cancellationReason;
    private String          status;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate       interestCalculationDate;
    private BigDecimal      interestRate;
    private BigDecimal      coefficient;
    private CoefficientDirectionEnum coefficientAction;
    private BigDecimal      minimumInterestRate;
    private BigDecimal      minimumInterestRateCoefficient;
    private CoefficientDirectionEnum minimumInterestRateCoefficientAction;
    private BigDecimal      maximalnterestRate;
    private BigDecimal      maximalnterestRateCoefficient;
    private CoefficientDirectionEnum maximalnterestRateCoefficientAction;
}