package ru.stonesk.estimator.repository;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.stonesk.estimator.model.entity.BuildingObject;
import ru.stonesk.estimator.model.entity.nomenclature.Nomenclature;
import ru.stonesk.estimator.model.entity.nomenclature.NomenclatureType;

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
class NomenclatureRepositoryTests {

    @Autowired
    private NomenclatureRepository repository;

    @Test
    void assertEntityWithAllFields() {
        Field[] declaredFields = Nomenclature.class.getDeclaredFields();
        Field[] declaredSuperclassFields = BuildingObject.class.getSuperclass().getDeclaredFields();
        List<Field> fields = new ArrayList<>(Arrays.asList(declaredFields));
        fields.addAll(Arrays.asList(declaredSuperclassFields));
        Map<String, ? extends Class<?>> expectedFields = fields.stream()
                .collect(Collectors.toMap(Field::getName, Field::getType));
        Map<String, ? extends Class<?>> actualFields = Map.of(
                "id", Integer.class,
                "type", NomenclatureType.class,
                "material", String.class,
                "name", String.class,
                "product", String.class,
                "sizes", String.class,
                "measurementUnit", String.class,
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
        Nomenclature nomenclature = Nomenclature.builder()
                .type(NomenclatureType.MATERIAL)
                .material("Материал")
                .name("Название")
                .product("Изделие")
                .sizes("0000x0000x0000")
                .measurementUnit("Единица Измерения")
                .deleted(false)
                .build();

        repository.save(nomenclature);
        Nomenclature actualNomenclature = repository.findById(nomenclature.getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        assertEquals(nomenclature.getId(), actualNomenclature.getId());
        assertEquals(nomenclature.getType(), actualNomenclature.getType());
        assertEquals(nomenclature.getMaterial(), actualNomenclature.getMaterial());
        assertEquals(nomenclature.getName(), actualNomenclature.getName());
        assertEquals(nomenclature.getProduct(), actualNomenclature.getProduct());
        assertEquals(nomenclature.getSizes(), actualNomenclature.getSizes());
        assertEquals(nomenclature.getMeasurementUnit(), actualNomenclature.getMeasurementUnit());
        assertEquals(nomenclature.getDeleted(), actualNomenclature.getDeleted());
        assertEquals(nomenclature.getDeletionDate(), actualNomenclature.getDeletionDate());
    }
}