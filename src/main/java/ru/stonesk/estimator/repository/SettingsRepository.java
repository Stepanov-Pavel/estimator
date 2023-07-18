package ru.stonesk.estimator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stonesk.estimator.model.entity.Settings;

import java.util.List;

public interface SettingsRepository extends JpaRepository<Settings, Integer> {

    @Override
    default <S extends Settings> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    default <S extends Settings> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    default void deleteAllInBatch(Iterable<Settings> entities) {
    }

    @Override
    default void deleteAllByIdInBatch(Iterable<Integer> integers) {
    }

    @Override
    default void deleteAllInBatch() {
    }

    @Override
    default <S extends Settings> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    default <S extends Settings> S save(S entity) {
        return null;
    }

    @Override
    default void deleteById(Integer integer) {
    }

    @Override
    default void delete(Settings entity) {
    }

    @Override
    default void deleteAllById(Iterable<? extends Integer> integers) {
    }

    @Override
    default void deleteAll(Iterable<? extends Settings> entities) {
    }

    @Override
    default void deleteAll() {
    }
}