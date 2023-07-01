package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * File storage for which estimate is calculated
 */
@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class EstimateFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob()
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "estimate_id")
    private Estimate estimates;

    private String type;
    private String nameWithExtension;
}
