package ru.stonesk.estimator.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * File storage for which estimate is calculated
 */
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EstimateFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob()
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "estimate_id")
    private Estimate estimate;

    private String type;
    private String nameWithExtension;
}