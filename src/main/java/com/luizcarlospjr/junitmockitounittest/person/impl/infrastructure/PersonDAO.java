package com.luizcarlospjr.junitmockitounittest.person.impl.infrastructure;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private static final List<PersonEntity> persons = new ArrayList<>();
    private static final long DEFAULT_FIRST_ID = 0L;
    private static final long ID_STEP_SIZE = 1L;

    public PersonEntity save(PersonEntity entity) {
        if (entity.getId() == null) {
            Long nextId = getNextId();
            entity.setId(nextId);
        } else {
            removePerson(entity.getId());
        }

        persons.add(entity);

        return PersonEntity.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cpf(entity.getCpf())
                .build();
    }

    public List<PersonEntity> findAll() {
        return new ArrayList<>(persons);
    }

    public void deleteAll() {
        persons.removeAll(persons);
    }


    public Optional<PersonEntity> findById(Long personId) {
        return persons.stream()
                .filter(person -> person.getId().equals(personId))
                .findFirst();
    }

    private void removePerson(Long id) {
        for (PersonEntity person : persons) {
            if (person.getId().equals(id)) {
                persons.remove(person);
                break;
            }
        }
    }

    private Long getNextId() {
        Long lastId = persons.stream()
                .sorted(Comparator.comparing(PersonEntity::getId).reversed())
                .findFirst()
                .map(PersonEntity::getId)
                .orElse(DEFAULT_FIRST_ID);

        return lastId + ID_STEP_SIZE;
    }

}
