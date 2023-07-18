package ru.stonesk.estimator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stonesk.estimator.model.entity.EstimateItem;

public interface EstimateItemRepository extends JpaRepository<EstimateItem, Integer> {
}