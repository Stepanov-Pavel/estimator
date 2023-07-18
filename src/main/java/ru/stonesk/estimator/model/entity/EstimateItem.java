package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.stonesk.estimator.model.entity.nomenclature.Nomenclature;

import java.math.BigDecimal;

/**
 * Representation of calculation each estimate record
 */
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EstimateItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "estimate_id")
    private Estimate estimate;

    @ManyToOne
    @JoinColumn(name = "nomenclature_id")
    private Nomenclature nomenclature;

    private String processing;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal amount;
    private Integer discount;
    private BigDecimal priceWithDiscount;
    private BigDecimal amountWithDiscount;
}