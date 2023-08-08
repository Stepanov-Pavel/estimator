package ru.stonesk.estimator.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Storing application settings
 */
@Entity
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