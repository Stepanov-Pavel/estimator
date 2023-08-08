package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.stonesk.estimator.model.entity.nomenclature.Nomenclature;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Prices of nomenclature items with expiration dates
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "nomenclature_id")
    private Nomenclature nomenclature;

    private BigDecimal cost;
    private LocalDate dateStart;
    private LocalDate dateEnd;
}