package ru.stonesk.estimator.model.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@NoArgsConstructor
@SuperBuilder
public abstract class SoftDeletable {
    private Boolean deleted;
    private LocalDate deletionDate;
}