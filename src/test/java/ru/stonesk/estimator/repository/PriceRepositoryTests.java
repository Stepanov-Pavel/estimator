package ru.stonesk.estimator.repository;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import ru.stonesk.estimator.model.entity.Price;
import ru.stonesk.estimator.model.entity.nomenclature.Nomenclature;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PriceRepositoryTests {

    @Autowired
    private PriceRepository repository;

    @Test
    void assertEntityWithAllFields() {
        Field[] declaredFields = Price.class.getDeclaredFields();
        Map<String, ? extends Class<?>> expectedFields = Arrays.stream(declaredFields)
                .collect(Collectors.toMap(Field::getName, Field::getType));
        Map<String, ? extends Class<?>> actualFields = Map.of(
                "id", Integer.class,
                "nomenclature", Nomenclature.class,
                "cost", BigDecimal.class,
                "dateStart", LocalDate.class,
                "dateEnd", LocalDate.class
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
    @Sql("/db/data/priceRepositoryTest_saveEntityWithAllFields.sql")
    void saveEntityWithAllFields() {
        Price price = Price.builder()
                .nomenclature(Nomenclature.builder().id(1).build())
                .cost(BigDecimal.valueOf(1000000))
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.parse("2023-12-31"))
                .build();

        repository.save(price);
        Price actualPrice = repository.findById(price.getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        assertEquals(price.getId(), actualPrice.getId());
        assertEquals(price.getNomenclature(), actualPrice.getNomenclature());
        assertEquals(price.getCost(), actualPrice.getCost());
        assertEquals(price.getDateStart(), actualPrice.getDateStart());
        assertEquals(price.getDateEnd(), actualPrice.getDateEnd());
    }
}