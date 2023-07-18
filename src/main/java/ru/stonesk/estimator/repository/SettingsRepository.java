package ru.stonesk.estimator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stonesk.estimator.model.entity.Settings;

public interface SettingsRepository extends JpaRepository<Settings, Integer> {
}