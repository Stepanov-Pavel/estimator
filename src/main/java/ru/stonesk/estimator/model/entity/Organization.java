package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Organization that performs calculation of estimate
 */
@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String addr;
    private String telephone;
    private String website;
    private String email;
    private Boolean deleted;
    private LocalDate deletionDate;
}
