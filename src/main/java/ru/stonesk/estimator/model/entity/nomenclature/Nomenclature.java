package ru.stonesk.estimator.model.entity.nomenclature;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Information about materials, production works and services
 */
@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Nomenclature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private NomenclatureType type;

    private String material;
    private String name;
    private String product;
    private String sizes;
    private String measurement_unit;
    private Boolean deleted;
    private LocalDate deletionDate;
}
