package ru.stonesk.estimator.model.entity.nomenclature;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import ru.stonesk.estimator.model.entity.SoftDeletable;

/**
 * Information about materials, production works and services
 */
@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = """
        UPDATE nomenclature
            SET deleted = true, deletion_date = CURRENT_DATE
        WHERE id = ?
        """)
@FilterDef(name = "deletedNomenclatureFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedNomenclatureFilter", condition = "deleted = :isDeleted")
public class Nomenclature extends SoftDeletable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private NomenclatureType type;

    private String material;
    private String name;
    private String product;
    private String sizes;
    private String measurementUnit;
}