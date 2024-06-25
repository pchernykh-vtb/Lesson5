package ru.vtb.lesson5.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vtb.lesson5.model.CoefficientDirectionEnum;
import ru.vtb.lesson5.model.ProductTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "agreement", schema = "public", catalog = "lesson5")
@Getter
@Setter
@NoArgsConstructor
public class AgreementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private Integer productId;
    private String generalAgreementId;
    private String supplementaryAgreementId;
    
    private ProductTypeEnum arrangementType;
    private Long shedulerJobId;
    private String number;
    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
    private LocalDateTime cancelDate;
    private Long validityDuration;
    private String cancellationReason;
    private String status;
    private LocalDateTime interestCalculationDate;
    private BigDecimal interestRate;
    private BigDecimal coefficient;
    @Enumerated(EnumType.STRING)
    private CoefficientDirectionEnum coefficientAction;
    private BigDecimal minimumInterestRate;
    private BigDecimal minimumInterestRateCoefficient;
    @Enumerated(EnumType.STRING)
    private CoefficientDirectionEnum minimumInterestRateCoefficientAction;
    private BigDecimal maximalInterestRate;
    private BigDecimal maximalInterestRateCoefficient;
    @Enumerated(EnumType.STRING)
    private CoefficientDirectionEnum maximalInterestRateCoefficientAction;

    // Все, кроме ID
    public AgreementEntity(Integer productId, String generalAgreementId, String supplementaryAgreementId, ProductTypeEnum arrangementType, Long shedulerJobId, String number, LocalDateTime openingDate, LocalDateTime closingDate, LocalDateTime cancelDate, Long validityDuration, String cancellationReason, String status, LocalDateTime interestCalculationDate, BigDecimal interestRate, BigDecimal coefficient, CoefficientDirectionEnum coefficientAction, BigDecimal minimumInterestRate, BigDecimal minimumInterestRateCoefficient, CoefficientDirectionEnum minimumInterestRateCoefficientAction, BigDecimal maximalInterestRate, BigDecimal maximalInterestRateCoefficient, CoefficientDirectionEnum maximalInterestRateCoefficientAction) {
        this.productId = productId;
        this.generalAgreementId = generalAgreementId;
        this.supplementaryAgreementId = supplementaryAgreementId;
        this.arrangementType = arrangementType;
        this.shedulerJobId = shedulerJobId;
        this.number = number;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.cancelDate = cancelDate;
        this.validityDuration = validityDuration;
        this.cancellationReason = cancellationReason;
        this.status = status;
        this.interestCalculationDate = interestCalculationDate;
        this.interestRate = interestRate;
        this.coefficient = coefficient;
        this.coefficientAction = coefficientAction;
        this.minimumInterestRate = minimumInterestRate;
        this.minimumInterestRateCoefficient = minimumInterestRateCoefficient;
        this.minimumInterestRateCoefficientAction = minimumInterestRateCoefficientAction;
        this.maximalInterestRate = maximalInterestRate;
        this.maximalInterestRateCoefficient = maximalInterestRateCoefficient;
        this.maximalInterestRateCoefficientAction = maximalInterestRateCoefficientAction;
    }
}
