package ru.stonesk.estimator.repository;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import ru.stonesk.estimator.model.entity.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class EstimateRepositoryTests {

    @Autowired
    private EstimateRepository repository;

    @Test
    void assertEntityWithAllFields() {
        Field[] declaredFields = Estimate.class.getDeclaredFields();
        Map<String, ? extends Class<?>> expectedFields = Arrays.stream(declaredFields)
                .collect(Collectors.toMap(Field::getName, Field::getType));
        Map<String, ? extends Class<?>> actualFields = Map.ofEntries(
                entry("id", Integer.class),
                entry("person", Person.class),
                entry("organization", Organization.class),
                entry("buildingObject", BuildingObject.class),
                entry("customer", Customer.class),
                entry("name", String.class),
                entry("number", String.class),
                entry("date", LocalDate.class),
                entry("comment", String.class),
                entry("total", BigDecimal.class),
                entry("totalWithDiscount", BigDecimal.class),
                entry("isDraft", Boolean.class),
                entry("deleted", Boolean.class),
                entry("deletionDate", LocalDate.class)
        );

        String[] expectedFieldNames = expectedFields.keySet().toArray(new String[0]);
        String[] actualFieldNames = actualFields.keySet().toArray(new String[0]);
        Arrays.sort(expectedFieldNames);
        Arrays.sort(actualFieldNames);

        Object[] expectedFieldClassNames = expectedFields.values().stream()
                .map(Class::getSimpleName)
                .sorted()
                .toArray();
        Object[] actualFieldClassNames = actualFields.values().stream()
                .map(Class::getSimpleName)
                .sorted()
                .toArray();

        assertEquals(expectedFields.size(), actualFields.size());
        assertArrayEquals(expectedFieldNames, actualFieldNames);
        assertArrayEquals(expectedFieldClassNames, actualFieldClassNames);
    }

    @Test
    @Sql("/db/data/estimateRepositoryTest_saveEntityWithAllFields.sql")
    void saveEntityWithAllFields() {
        Estimate estimate = Estimate.builder()
                .person(Person.builder().id(1).build())
                .organization(Organization.builder().id(1).build())
                .buildingObject(BuildingObject.builder().id(1).build())
                .customer(Customer.builder().id(1).build())
                .name("Название")
                .number("0000_ПП")
                .date(LocalDate.now())
                .comment("Коментарий")
                .total(BigDecimal.valueOf(10000))
                .totalWithDiscount(BigDecimal.valueOf(1000))
                .isDraft(false)
                .deleted(false)
                .build();

        repository.save(estimate);
        Estimate actualEstimate = repository.findById(estimate.getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        assertEquals(estimate.getId(), actualEstimate.getId());
        assertEquals(estimate.getPerson(), actualEstimate.getPerson());
        assertEquals(estimate.getOrganization(), actualEstimate.getOrganization());
        assertEquals(estimate.getBuildingObject(), actualEstimate.getBuildingObject());
        assertEquals(estimate.getCustomer(), actualEstimate.getCustomer());
        assertEquals(estimate.getName(), actualEstimate.getName());
        assertEquals(estimate.getNumber(), actualEstimate.getNumber());
        assertEquals(estimate.getDate(), actualEstimate.getDate());
        assertEquals(estimate.getComment(), actualEstimate.getComment());
        assertEquals(estimate.getTotal(), actualEstimate.getTotal());
        assertEquals(estimate.getTotalWithDiscount(), actualEstimate.getTotalWithDiscount());
        assertEquals(estimate.getIsDraft(), actualEstimate.getIsDraft());
        assertEquals(estimate.getDeleted(), actualEstimate.getDeleted());
        assertEquals(estimate.getDeletionDate(), actualEstimate.getDeletionDate());
    }
}