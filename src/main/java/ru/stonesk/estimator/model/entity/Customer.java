package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Client (natural person) for which estimate is calculated
 */
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String telephone;
    private Boolean deleted;
    private LocalDate deletionDate;
}