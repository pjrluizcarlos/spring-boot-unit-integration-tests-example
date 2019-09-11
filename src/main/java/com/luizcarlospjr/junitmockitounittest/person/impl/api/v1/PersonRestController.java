package com.luizcarlospjr.junitmockitounittest.person.impl.api.v1;

import com.luizcarlospjr.junitmockitounittest.NotFoundException;
import com.luizcarlospjr.junitmockitounittest.person.api.v1.PersonInputDTO;
import com.luizcarlospjr.junitmockitounittest.person.api.v1.PersonOutputDTO;
import com.luizcarlospjr.junitmockitounittest.person.api.v1.PersonUpdateDTO;
import com.luizcarlospjr.junitmockitounittest.person.impl.business.PersonBO;
import com.luizcarlospjr.junitmockitounittest.person.impl.business.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/person/v1/persons")
@RequiredArgsConstructor
public class PersonRestController {

    private final PersonApiConverter personApiConverter;
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonOutputDTO> createPerson(@RequestBody PersonInputDTO personToCreate) {
        PersonBO person = personApiConverter.toBO(personToCreate);
        PersonBO createdPerson = personService.createPerson(person);

        return ResponseEntity.status(HttpStatus.CREATED).body(personApiConverter.toDTO(createdPerson));
    }

    @GetMapping
    public ResponseEntity<List<PersonOutputDTO>> findAllPersons() {
        List<PersonBO> foundPersons = personService.findAllPersons();
        List<PersonOutputDTO> persons = personApiConverter.toDTO(foundPersons);

        return ResponseEntity.ok(persons);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<PersonOutputDTO> updatePerson(@PathVariable("personId") Long personId,
                                                        @RequestBody PersonUpdateDTO updateDTO) {
        PersonBO personToUpdate = personApiConverter.toBO(personId, updateDTO);
        PersonBO updatedPerson = personService.updatePerson(personToUpdate);

        return ResponseEntity.ok(personApiConverter.toDTO(updatedPerson));
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonOutputDTO> findPersonById(@PathVariable("personId") Long personId) {
        Optional<PersonBO> foundPerson = personService.findPersonById(personId);
        PersonBO person = foundPerson.orElseThrow(() -> new NotFoundException("Person not found with ID " + personId));
        return ResponseEntity.ok(personApiConverter.toDTO(person));
    }

}
