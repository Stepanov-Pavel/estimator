package ru.stonesk.estimator.repository;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import ru.stonesk.estimator.model.entity.Estimate;
import ru.stonesk.estimator.model.entity.EstimateItem;
import ru.stonesk.estimator.model.entity.nomenclature.Nomenclature;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class EstimateItemRepositoryTests {

    @Autowired
    private EstimateItemRepository repository;

    @Test
    void assertEntityWithAllFields() {
        Field[] declaredFields = EstimateItem.class.getDeclaredFields();
        Map<String, ? extends Class<?>> expectedFields = Arrays.stream(declaredFields)
                .collect(Collectors.toMap(Field::getName, Field::getType));
        Map<String, ? extends Class<?>> actualFields = Map.of(
                "id", Integer.class,
                "estimate", Estimate.class,
                "nomenclature", Nomenclature.class,
                "processing", String.class,
                "quantity", BigDecimal.class,
                "price", BigDecimal.class,
                "amount", BigDecimal.class,
                "discount", Integer.class,
                "priceWithDiscount", BigDecimal.class,
                "amountWithDiscount", BigDecimal.class
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
    @Sql("/db/data/estimateItemRepositoryTest_saveEntityWithAllFields.sql")
    void saveEntityWithAllFields() {
        EstimateItem estimateItem = EstimateItem.builder()
                .estimate(Estimate.builder().id(1).build())
                .nomenclature(Nomenclature.builder().id(1).build())
                .processing("Обработка")
                .quantity(BigDecimal.valueOf(1000))
                .price(BigDecimal.valueOf(1000))
                .amount(BigDecimal.valueOf(100000))
                .discount(100)
                .priceWithDiscount(BigDecimal.valueOf(1000))
                .amountWithDiscount(BigDecimal.valueOf(1000))
                .build();

        repository.save(estimateItem);
        EstimateItem actualEstimateItem = repository.findById(estimateItem.getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        assertEquals(estimateItem.getId(), actualEstimateItem.getId());
        assertEquals(estimateItem.getEstimate(), actualEstimateItem.getEstimate());
        assertEquals(estimateItem.getNomenclature(), actualEstimateItem.getNomenclature());
        assertEquals(estimateItem.getProcessing(), actualEstimateItem.getProcessing());
        assertEquals(estimateItem.getQuantity(), actualEstimateItem.getQuantity());
        assertEquals(estimateItem.getPrice(), actualEstimateItem.getPrice());
        assertEquals(estimateItem.getAmount(), actualEstimateItem.getAmount());
        assertEquals(estimateItem.getDiscount(), actualEstimateItem.getDiscount());
        assertEquals(estimateItem.getPriceWithDiscount(), actualEstimateItem.getPriceWithDiscount());
        assertEquals(estimateItem.getAmountWithDiscount(), actualEstimateItem.getAmountWithDiscount());
    }
}