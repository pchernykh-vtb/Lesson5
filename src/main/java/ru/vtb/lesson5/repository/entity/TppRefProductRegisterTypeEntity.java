package ru.vtb.lesson5.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "tpp_ref_product_register_type", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class TppRefProductRegisterTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int internalId;
    private String value;
    private String registerTypeName;
    private String productClassCode;
    private Timestamp registerTypeStartDate;
    private Timestamp registerTypeEndDate;
    private String accountType;

    // Все, кроме ID
    public TppRefProductRegisterTypeEntity(String value, String registerTypeName, String productClassCode, Timestamp registerTypeStartDate, Timestamp registerTypeEndDate, String accountType) {
        this.value = value;
        this.registerTypeName = registerTypeName;
        this.productClassCode = productClassCode;
        this.registerTypeStartDate = registerTypeStartDate;
        this.registerTypeEndDate = registerTypeEndDate;
        this.accountType = accountType;
    }
}
