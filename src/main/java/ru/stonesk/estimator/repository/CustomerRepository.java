package ru.stonesk.estimator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.stonesk.estimator.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * Set field "deleted" on value FALSE, and field "deletionDate" on value NULL
     *
     * @param id entity identifier
     * @return number modified rows
     */
    @Modifying
    @Query("""
            UPDATE Customer c
                SET c.deleted = false, c.deletionDate = null
            WHERE id = :id
            """)
    int modifyDeletionMarkOnFalse(int id);
}