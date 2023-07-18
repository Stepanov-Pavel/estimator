package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * General information about calculations of estimate
 */
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Estimate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "building_object_id")
    private BuildingObject buildingObject;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String name;
    private String number;
    private LocalDate date;
    private String comment;
    private BigDecimal total;
    private BigDecimal totalWithDiscount;
    private Boolean isDraft;
    private Boolean deleted;
    private LocalDate deletionDate;
}