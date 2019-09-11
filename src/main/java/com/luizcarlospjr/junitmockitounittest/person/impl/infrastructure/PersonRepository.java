package com.luizcarlospjr.junitmockitounittest.person.impl.infrastructure;

import com.luizcarlospjr.junitmockitounittest.person.impl.business.PersonBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonRepository {

    private final PersonEntityConverter personEntityConverter;
    private final PersonDAO personDAO;

    public PersonBO createPerson(PersonBO bo) {
        PersonEntity personToCreate = personEntityConverter.toEntity(bo);
        PersonEntity createdPerson = personDAO.save(personToCreate);
        return personEntityConverter.toBO(createdPerson);
    }

    public List<PersonBO> findAllPersons() {
        return personEntityConverter.toBO(personDAO.findAll());
    }

    public PersonBO updatePerson(PersonBO bo) {
        PersonEntity personToUpdate = personEntityConverter.toEntity(bo);
        PersonEntity updatedPerson = personDAO.save(personToUpdate);

        return personEntityConverter.toBO(updatedPerson);
    }

    public Optional<PersonBO> findById(Long personId) {
        return personDAO.findById(personId)
                .map(personEntityConverter::toBO);
    }
}
