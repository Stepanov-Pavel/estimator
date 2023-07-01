package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Construction object for which estimate is calculated
 */
@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class BuildingObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer type;
    private Integer addr;
    private Integer note;
    private Boolean deleted;
    private LocalDate deletionDate;
}
