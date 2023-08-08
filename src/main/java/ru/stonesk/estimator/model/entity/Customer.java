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
 * Client (natural person) for which estimate is calculated
 */
@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = """
        UPDATE customer
            SET deleted = true, deletion_date = CURRENT_DATE
        WHERE id = ?
        """)
@FilterDef(name = "deletedCustomerFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedCustomerFilter", condition = "deleted = :isDeleted")
public class Customer extends SoftDeletable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String telephone;
}