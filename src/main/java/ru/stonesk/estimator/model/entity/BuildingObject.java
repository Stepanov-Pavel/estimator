package ru.stonesk.estimator.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

/**
 * Construction object for which estimate is calculated
 */
@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = """
        UPDATE building_object
            SET deleted = true, deletion_date = CURRENT_DATE
        WHERE id = ?
        """)
@FilterDef(name = "deletedBuildingObjectFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedBuildingObjectFilter", condition = "deleted = :isDeleted")
public class BuildingObject extends SoftDeletable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;
    private String addr;
    private String note;
}