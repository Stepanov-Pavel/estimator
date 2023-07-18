package ru.stonesk.estimator.repository;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.stonesk.estimator.model.entity.Organization;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class OrganizationRepositoryTests {

    @Autowired
    private OrganizationRepository repository;

    @Test
    void assertEntityWithAllFields() {
        Field[] declaredFields = Organization.class.getDeclaredFields();
        Map<String, ? extends Class<?>> expectedFields = Arrays.stream(declaredFields)
                .collect(Collectors.toMap(Field::getName, Field::getType));
        Map<String, ? extends Class<?>> actualFields = Map.of(
                "id", Integer.class,
                "name", String.class,
                "addr", String.class,
                "telephone", String.class,
                "website", String.class,
                "email", String.class,
                "deleted", Boolean.class,
                "deletionDate", LocalDate.class
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
    void saveWithAllFields() {
        Organization organization = Organization.builder()
                .name("Организация")
                .addr("Город, Улица, Дом, Подъезд, Этаж, Офис")
                .telephone("+7(000)000-00-00")
                .website("www.website.ru")
                .email("e-mail@yandex.ru")
                .deleted(false)
                .build();

        repository.save(organization);
        Organization actualOrganization = repository.findById(organization.getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        assertEquals(organization.getId(), actualOrganization.getId());
        assertEquals(organization.getName(), actualOrganization.getName());
        assertEquals(organization.getAddr(), actualOrganization.getAddr());
        assertEquals(organization.getTelephone(), actualOrganization.getTelephone());
        assertEquals(organization.getWebsite(), actualOrganization.getWebsite());
        assertEquals(organization.getEmail(), actualOrganization.getEmail());
        assertEquals(organization.getDeleted(), actualOrganization.getDeleted());
        assertEquals(organization.getDeletionDate(), actualOrganization.getDeletionDate());
    }
}