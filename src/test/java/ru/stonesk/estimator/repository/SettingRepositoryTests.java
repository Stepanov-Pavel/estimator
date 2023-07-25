package ru.stonesk.estimator.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.stonesk.estimator.model.entity.Setting;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class SettingRepositoryTests {

    @Test
    void assertEntityWithAllFields() {
        Field[] declaredFields = Setting.class.getDeclaredFields();
        Map<String, ? extends Class<?>> expectedFields = Arrays.stream(declaredFields)
                .collect(Collectors.toMap(Field::getName, Field::getType));
        Map<String, ? extends Class<?>> actualFields = Map.of(
                "id", Integer.class,
                "estimateNumberStartValue", Integer.class,
                "estimateNumberMaxValue", Integer.class,
                "estimateNumberCurrentValue", Integer.class
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
}