package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Construction object for which estimate is calculated
 */
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BuildingObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;
    private String addr;
    private String note;
    private Boolean deleted;
    private LocalDate deletionDate;
}