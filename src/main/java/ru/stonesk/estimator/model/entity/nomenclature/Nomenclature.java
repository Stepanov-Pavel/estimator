package ru.stonesk.estimator.model.entity.nomenclature;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Information about materials, production works and services
 */
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
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
    private String measurementUnit;
    private Boolean deleted;
    private LocalDate deletionDate;
}