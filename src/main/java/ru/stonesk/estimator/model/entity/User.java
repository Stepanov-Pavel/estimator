package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * User of system (and the employee in one person) who calculates estimate
 */
@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String patronymic;
    private String username;
    private String password;
    private String email;
    private String telephone;
    private LocalDateTime lastLogin;
    private Boolean deleted;
    private LocalDate deletionDate;
}
