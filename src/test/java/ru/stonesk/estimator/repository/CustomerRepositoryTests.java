package ru.stonesk.estimator.repository;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.stonesk.estimator.model.entity.BuildingObject;
import ru.stonesk.estimator.model.entity.Customer;

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
class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository repository;

    @Test
    void assertEntityWithAllFields() {
        Field[] declaredFields = Customer.class.getDeclaredFields();
        Field[] declaredSuperclassFields = BuildingObject.class.getSuperclass().getDeclaredFields();
        List<Field> fields = new ArrayList<>(Arrays.asList(declaredFields));
        fields.addAll(Arrays.asList(declaredSuperclassFields));
        Map<String, ? extends Class<?>> expectedFields = fields.stream()
                .collect(Collectors.toMap(Field::getName, Field::getType));
        Map<String, ? extends Class<?>> actualFields = Map.of(
                "id", Integer.class,
                "name", String.class,
                "surname", String.class,
                "patronymic", String.class,
                "email", String.class,
                "telephone", String.class,
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
        Customer customer = Customer.builder()
                .name("Заказчик")
                .surname("Заказчиков")
                .patronymic("Заказчикович")
                .email("e-mail@yandex.ru")
                .telephone("+7(000)000-00-00")
                .deleted(false)
                .build();

        repository.save(customer);
        Customer actualCustomer = repository.findById(customer.getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        assertEquals(customer.getId(), actualCustomer.getId());
        assertEquals(customer.getName(), actualCustomer.getName());
        assertEquals(customer.getSurname(), actualCustomer.getSurname());
        assertEquals(customer.getPatronymic(), actualCustomer.getPatronymic());
        assertEquals(customer.getEmail(), actualCustomer.getEmail());
        assertEquals(customer.getTelephone(), actualCustomer.getTelephone());
        assertEquals(customer.getDeleted(), actualCustomer.getDeleted());
        assertEquals(customer.getDeletionDate(), actualCustomer.getDeletionDate());
    }
}