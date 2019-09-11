package com.luizcarlospjr.junitmockitounittest.person.impl.api.v1.withoutmockandrestinterface;

import com.luizcarlospjr.junitmockitounittest.person.api.v1.PersonInputDTO;
import com.luizcarlospjr.junitmockitounittest.person.api.v1.PersonOutputDTO;
import com.luizcarlospjr.junitmockitounittest.person.impl.api.v1.PersonRestController;
import com.luizcarlospjr.junitmockitounittest.person.impl.infrastructure.PersonDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRestControllerIntegrationTest {

    public static final long FIRST_PERSON_ID = 1L;
    public static final int PERSON_QUANTITY_CREATED = 1;

    @Autowired
    private PersonRestController personRestController;

    @Autowired
    private PersonDAO personDAO;

    @Before
    public void setUp() {
        personDAO.deleteAll();
    }

    @Test
    public void shouldCreatePerson() {
        PersonInputDTO inputDTO = PersonInputDTO.builder()
                .cpf("02202202222")
                .name("Luiz")
                .build();

        ResponseEntity<PersonOutputDTO> createdPerson = personRestController.createPerson(inputDTO);
        assertThat(createdPerson.getStatusCode(), is(equalTo(HttpStatus.CREATED)));
        assertThat(createdPerson.getBody().getId(), is(equalTo(FIRST_PERSON_ID)));

        ResponseEntity<List<PersonOutputDTO>> allPersons = personRestController.findAllPersons();
        assertThat(allPersons.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertThat(allPersons.getBody().size(), is(equalTo(PERSON_QUANTITY_CREATED)));
        assertThat(allPersons.getBody().stream().findFirst().get().getId(), is(equalTo(FIRST_PERSON_ID)));
    }

}