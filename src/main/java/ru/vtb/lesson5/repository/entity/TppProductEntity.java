package ru.vtb.lesson5.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.vtb.lesson5.model.ProductTypeEnum;
import ru.vtb.lesson5.model.RateTypeEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tpp_product", schema = "public", catalog = "lesson5")
@Getter
@Setter
@NoArgsConstructor
public class TppProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private Long productCodeId;
    private Long clientId;
    @Enumerated(EnumType.ORDINAL)
    private ProductTypeEnum type;
    @Size(max = 50)
    private String number;
    private Long priority;
    private LocalDateTime dateOfConclusion;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Long days;
    private BigDecimal penaltyRate;
    private BigDecimal nso;
    private BigDecimal thresholdAmount;
    @Size(max = 50)
    private String requisiteType;
    @Enumerated(EnumType.ORDINAL)
    private RateTypeEnum interestRateType;
    private BigDecimal taxRate;
    @Size(max = 100)
    private String reasoneClose;
    @Size(max = 50)
    private String state;

    // Все, кроме ID
    public TppProductEntity(Long productCodeId, Long clientId, ProductTypeEnum type, String number, Long priority, LocalDateTime dateOfConclusion, LocalDateTime startDateTime, LocalDateTime endDateTime, Long days, BigDecimal penaltyRate, BigDecimal nso, BigDecimal thresholdAmount, String requisiteType, RateTypeEnum interestRateType, BigDecimal taxRate, String reasoneClose, String state) {
        this.productCodeId = productCodeId;
        this.clientId = clientId;
        this.type = type;
        this.number = number;
        this.priority = priority;
        this.dateOfConclusion = dateOfConclusion;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.days = days;
        this.penaltyRate = penaltyRate;
        this.nso = nso;
        this.thresholdAmount = thresholdAmount;
        this.requisiteType = requisiteType;
        this.interestRateType = interestRateType;
        this.taxRate = taxRate;
        this.reasoneClose = reasoneClose;
        this.state = state;
    }
}
