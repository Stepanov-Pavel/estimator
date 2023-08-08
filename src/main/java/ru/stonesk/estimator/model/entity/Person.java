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

import java.time.LocalDateTime;

/**
 * Person of system (and the employee in one person) who calculates estimate
 */
@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = """
        UPDATE person
            SET deleted = true, deletion_date = CURRENT_DATE
        WHERE id = ?
        """)
@FilterDef(name = "deletedPersonFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedPersonFilter", condition = "deleted = :isDeleted")
public class Person extends SoftDeletable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String patronymic;
    private String username;
    private String password;
    private String email;
    private String telephone;
    private LocalDateTime lastLogin;
}