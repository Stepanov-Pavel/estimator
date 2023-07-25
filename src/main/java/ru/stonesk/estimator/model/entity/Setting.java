package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Storing application settings
 */
@Entity
@Table
@NoArgsConstructor
@Getter
@Immutable
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer estimateNumberStartValue;
    private Integer estimateNumberMaxValue;
    private Integer estimateNumberCurrentValue;
}