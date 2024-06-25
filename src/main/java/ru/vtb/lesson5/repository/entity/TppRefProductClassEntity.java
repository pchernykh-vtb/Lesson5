package ru.vtb.lesson5.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tpp_ref_product_class", schema = "public", catalog = "lesson5")
@Getter
@Setter
@NoArgsConstructor
public class TppRefProductClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int internalId;
    private String value;
    private String gbiCode;
    private String gbiName;
    private String productRowCode;
    private String productRowName;
    private String subclassCode;
    private String subclassName;

    // Все, кроме ID
    public TppRefProductClassEntity(String value, String gbiCode, String gbiName, String productRowCode, String productRowName, String subclassCode, String subclassName) {
        this.value = value;
        this.gbiCode = gbiCode;
        this.gbiName = gbiName;
        this.productRowCode = productRowCode;
        this.productRowName = productRowName;
        this.subclassCode = subclassCode;
        this.subclassName = subclassName;
    }
}
