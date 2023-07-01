package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * General information about calculations of estimate
 */
@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Estimate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organizations;

    @ManyToOne
    @JoinColumn(name = "building_object_id")
    private BuildingObject buildingObjects;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customers;

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
