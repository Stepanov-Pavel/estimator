package ru.stonesk.estimator.repository;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.stonesk.estimator.model.entity.BuildingObject;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class BuildingObjectRepositoryTests {

    @Autowired
    private BuildingObjectRepository repository;

    @Test
    void assertEntityWithAllFields() {
        Field[] declaredFields = BuildingObject.class.getDeclaredFields();
        Field[] declaredSuperclassFields = BuildingObject.class.getSuperclass().getDeclaredFields();
        List<Field> fields = new ArrayList<>(Arrays.asList(declaredFields));
        fields.addAll(Arrays.asList(declaredSuperclassFields));
        Map<String, ? extends Class<?>> expectedFields = fields.stream()
                .collect(Collectors.toMap(Field::getName, Field::getType));
        Map<String, ? extends Class<?>> actualFields = Map.of(
                "id", Integer.class,
                "type", String.class,
                "addr", String.class,
                "note", String.class,
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
    void saveEntityWithAllFields() {
        BuildingObject buildingObject = BuildingObject.builder()
                .type("Тип")
                .addr("Город, Улица, Дом, Подъезд, Этаж, Офис")
                .note("Заметка")
                .deleted(false)
                .build();

        repository.save(buildingObject);
        BuildingObject actualBuildingObject = repository.findById(buildingObject.getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        assertEquals(buildingObject.getId(), actualBuildingObject.getId());
        assertEquals(buildingObject.getType(), actualBuildingObject.getType());
        assertEquals(buildingObject.getAddr(), actualBuildingObject.getAddr());
        assertEquals(buildingObject.getNote(), actualBuildingObject.getNote());
        assertEquals(buildingObject.getDeleted(), actualBuildingObject.getDeleted());
        assertEquals(buildingObject.getDeletionDate(), actualBuildingObject.getDeletionDate());
    }
}