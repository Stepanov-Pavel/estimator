package ru.stonesk.estimator.repository;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import ru.stonesk.estimator.model.entity.Estimate;
import ru.stonesk.estimator.model.entity.EstimateFile;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class EstimateFileRepositoryTests {

    @Autowired
    private EstimateFileRepository repository;

    @Test
    void assertEntityWithAllFields() {
        Field[] declaredFields = EstimateFile.class.getDeclaredFields();
        Map<String, ? extends Class<?>> expectedFields = Arrays.stream(declaredFields)
                .collect(Collectors.toMap(Field::getName, Field::getType));
        Map<String, ? extends Class<?>> actualFields = Map.of(
                "id", Integer.class,
                "file", byte[].class,
                "estimate", Estimate.class,
                "type", String.class,
                "nameWithExtension", String.class
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
    @Sql("/db/data/estimateFileRepositoryTest_saveEntityWithAllFields.sql")
    void saveEntityWithAllFields() {
        EstimateFile estimateFile = EstimateFile.builder()
                .file(new byte[]{0x00, 0x01, 0x03, 0x10})
                .estimate(Estimate.builder().id(1).build())
                .type("image/jpeg")
                .nameWithExtension("Picture.jpg")
                .build();

        repository.save(estimateFile);
        EstimateFile actualEstimateFile = repository.findById(estimateFile.getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        assertEquals(estimateFile.getId(), actualEstimateFile.getId());
        assertEquals(estimateFile.getFile(), actualEstimateFile.getFile());
        assertEquals(estimateFile.getEstimate(), actualEstimateFile.getEstimate());
        assertEquals(estimateFile.getType(), actualEstimateFile.getType());
        assertEquals(estimateFile.getNameWithExtension(), actualEstimateFile.getNameWithExtension());
    }
}