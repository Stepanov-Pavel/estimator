package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stonesk.estimator.model.entity.nomenclature.Nomenclature;

import java.math.BigDecimal;

/**
 * Representation of calculation each estimate record
 */
@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class EstimateItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "estimate_id")
    private Estimate estimates;

    @ManyToOne
    @JoinColumn(name = "nomenclature_id")
    private Nomenclature nomenclatures;

    private String processing;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal amount;
    private Integer discount;
    private BigDecimal priceWithDiscount;
    private BigDecimal amountWithDiscount;
}
