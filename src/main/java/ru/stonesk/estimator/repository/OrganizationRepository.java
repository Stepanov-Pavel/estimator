package ru.stonesk.estimator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.stonesk.estimator.model.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

    /**
     * Set field "deleted" on value TRUE, and field "deletionDate" on value current date
     * @param id entity identifier
     * @return number modified rows
     */
    @Modifying
    @Query("""
            UPDATE Organization o
                SET o.deleted = true, o.deletionDate = current_date
            WHERE id = :id""")
    int modifyDeletionMarkOnTrue(int id);

    /**
     * Set field "deleted" on value FALSE, and field "deletionDate" on value NULL
     * @param id entity identifier
     * @return number modified rows
     */
    @Modifying
    @Query("""
            UPDATE Organization o
                SET o.deleted = false, o.deletionDate = null
            WHERE id = :id""")
    int modifyDeletionMarkOnFalse(int id);
}