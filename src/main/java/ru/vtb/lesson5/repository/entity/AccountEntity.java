package ru.vtb.lesson5.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account", schema = "public", catalog = "lesson5")
@Getter
@Setter
@NoArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;
    private Integer accountPoolId;
    private String accountNumber;
    private Boolean bussy;

    // Все, кроме ID
    public AccountEntity(Integer accountPoolId, String accountNumber, Boolean bussy) {
        this.accountPoolId = accountPoolId;
        this.accountNumber = accountNumber;
        this.bussy = bussy;
    }
}
