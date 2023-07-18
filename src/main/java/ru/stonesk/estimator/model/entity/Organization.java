package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Organization that performs calculation of estimate
 */
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
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