package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

/**
 * Storing application settings
 */
@Entity
@Table
@NoArgsConstructor
@Getter
@Immutable
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer estimateNumberStartValue;
    private Integer estimateNumberMaxValue;
    private Integer estimateNumberCurrentValue;
}