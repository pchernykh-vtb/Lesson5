package ru.vtb.lesson5.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.vtb.lesson5.model.TppProductRegistryStateEnum;

@Entity
@Table(name = "tpp_product_register")
@Getter
@Setter
@NoArgsConstructor
public class TppProductRegisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private Long productId;
    @NotNull
    @Size(max = 100)
    private String type;
    private Long account;
    @Size(max = 30)
    private String currencyCode;
    @Enumerated(EnumType.ORDINAL)
    private TppProductRegistryStateEnum state;
    @Size(max = 25)
    private String accountNumber;

    // Все, кроме ID
    public TppProductRegisterEntity(Long productId, String type, Long account, String currencyCode, TppProductRegistryStateEnum state, String accountNumber) {
        this.productId = productId;
        this.type = type;
        this.account = account;
        this.currencyCode = currencyCode;
        this.state = state;
        this.accountNumber = accountNumber;
    }
}
