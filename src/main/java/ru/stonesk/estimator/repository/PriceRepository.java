package ru.stonesk.estimator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stonesk.estimator.model.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Integer> {
}