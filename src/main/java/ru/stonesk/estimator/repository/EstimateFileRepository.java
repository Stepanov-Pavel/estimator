package ru.stonesk.estimator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stonesk.estimator.model.entity.EstimateFile;

public interface EstimateFileRepository extends JpaRepository<EstimateFile, Integer> {
}