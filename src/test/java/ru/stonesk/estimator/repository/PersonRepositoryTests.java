package ru.stonesk.estimator.repository;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.stonesk.estimator.model.entity.BuildingObject;
import ru.stonesk.estimator.model.entity.Person;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PersonRepositoryTests {

    @Autowired
    private PersonRepository repository;

    @Test
    void assertEntityWithAllFields() {
        Field[] declaredFields = Person.class.getDeclaredFields();
        Field[] declaredSuperclassFields = BuildingObject.class.getSuperclass().getDeclaredFields();
        List<Field> fields = new ArrayList<>(Arrays.asList(declaredFields));
        fields.addAll(Arrays.asList(declaredSuperclassFields));
        Map<String, ? extends Class<?>> expectedFields = fields.stream()
                .collect(Collectors.toMap(Field::getName, Field::getType));
        Map<String, ? extends Class<?>> actualFields = Map.ofEntries(
                entry("id", Integer.class),
                entry("name", String.class),
                entry("surname", String.class),
                entry("patronymic", String.class),
                entry("username", String.class),
                entry("password", String.class),
                entry("email", String.class),
                entry("telephone", String.class),
                entry("lastLogin", LocalDateTime.class),
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
    void saveEntityWithAllFields() {
        Person person = Person.builder()
                .name("Пользователь")
                .surname("Пользаков")
                .patronymic("Пользакович")
                .username("PolzovatelPP")
                .password("Пароль")
                .email("e-mail@yandex.ru")
                .telephone("+7(000)000-00-00")
                .lastLogin(LocalDateTime.now())
                .deleted(false)
                .build();

        repository.save(person);
        Person actualUser = repository.findById(person.getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        assertEquals(person.getId(), actualUser.getId());
        assertEquals(person.getName(), actualUser.getName());
        assertEquals(person.getSurname(), actualUser.getSurname());
        assertEquals(person.getPatronymic(), actualUser.getPatronymic());
        assertEquals(person.getUsername(), actualUser.getUsername());
        assertEquals(person.getPassword(), actualUser.getPassword());
        assertEquals(person.getEmail(), actualUser.getEmail());
        assertEquals(person.getTelephone(), actualUser.getTelephone());
        assertEquals(person.getLastLogin(), actualUser.getLastLogin());
        assertEquals(person.getDeleted(), actualUser.getDeleted());
        assertEquals(person.getDeletionDate(), actualUser.getDeletionDate());
    }
}