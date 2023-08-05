package ru.stonesk.estimator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.stonesk.estimator.model.entity.Estimate;

public interface EstimateRepository extends JpaRepository<Estimate, Integer> {

    /**
     * Set field "deleted" on value FALSE, and field "deletionDate" on value NULL
     *
     * @param id entity identifier
     * @return number modified rows
     */
    @Modifying
    @Query("""
            UPDATE Estimate e
                SET e.deleted = false, e.deletionDate = null
            WHERE id = :id
            """)
    int modifyDeletionMarkOnFalse(int id);
}