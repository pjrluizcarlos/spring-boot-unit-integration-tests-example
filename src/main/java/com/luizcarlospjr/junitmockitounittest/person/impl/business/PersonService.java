package com.luizcarlospjr.junitmockitounittest.person.impl.business;

import com.luizcarlospjr.junitmockitounittest.person.impl.infrastructure.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonValidator personValidator;
    private final PersonRepository personRepository;

    public PersonBO createPerson(PersonBO bo) {
        personValidator.validateCreatePerson(bo);
        return personRepository.createPerson(bo);
    }

    public List<PersonBO> findAllPersons() {
        return personRepository.findAllPersons();
    }

    public PersonBO updatePerson(PersonBO bo) {
        personValidator.validateUpdatePerson(bo);
        return personRepository.updatePerson(bo);
    }

    public Optional<PersonBO> findPersonById(Long personId) {
        return personRepository.findById(personId);
    }
}
