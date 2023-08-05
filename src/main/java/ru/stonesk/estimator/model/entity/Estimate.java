package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * General information about calculations of estimate
 */
@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = """
        UPDATE estimate
            SET deleted = true, deletion_date = CURRENT_DATE
        WHERE id = ?
        """)
@FilterDef(name = "deletedEstimateFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedEstimateFilter", condition = "deleted = :isDeleted")
public class Estimate extends SoftDeletable {

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
}