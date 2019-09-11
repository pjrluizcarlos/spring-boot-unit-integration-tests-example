package com.luizcarlospjr.junitmockitounittest.person.impl.api.v1.withmockwithourestinterface;

import com.luizcarlospjr.junitmockitounittest.person.api.v1.PersonInputDTO;
import com.luizcarlospjr.junitmockitounittest.person.api.v1.PersonOutputDTO;
import com.luizcarlospjr.junitmockitounittest.person.impl.api.v1.PersonRestController;
import com.luizcarlospjr.junitmockitounittest.person.impl.infrastructure.PersonDAO;
import com.luizcarlospjr.junitmockitounittest.person.impl.infrastructure.PersonEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRestControllerIntegrationTest {

    private static final long FIRST_PERSON_ID = 1L;
    private static final int PERSON_QUANTITY_CREATED = 1;
    private static final String PERSON_CPF = "02202202222";
    private static final String PERSON_NAME = "Luiz";
    private static final Long PERSON_ID = 1L;

    @Autowired
    private PersonRestController personRestController;

    @MockBean
    private PersonDAO personDAO;

    @Test
    public void shouldCreatePerson() {
        PersonEntity personEntity = PersonEntity.builder()
                .cpf(PERSON_CPF)
                .name(PERSON_NAME)
                .build();

        PersonEntity personEntityWithId = PersonEntity.builder()
                .id(PERSON_ID)
                .name(PERSON_NAME)
                .cpf(PERSON_CPF)
                .build();

        given(personDAO.save(personEntity)).willReturn(personEntityWithId);
        given(personDAO.findAll()).willReturn(asList(personEntityWithId));

        PersonInputDTO inputDTO = PersonInputDTO.builder()
                .cpf(PERSON_CPF)
                .name(PERSON_NAME)
                .build();

        ResponseEntity<PersonOutputDTO> createdPerson = personRestController.createPerson(inputDTO);
        assertThat(createdPerson.getStatusCode(), is(equalTo(HttpStatus.CREATED)));
        assertThat(createdPerson.getBody().getId(), is(equalTo(FIRST_PERSON_ID)));

        verify(personDAO).save(personEntity);

        ResponseEntity<List<PersonOutputDTO>> allPersons = personRestController.findAllPersons();
        assertThat(allPersons.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertThat(allPersons.getBody().size(), is(equalTo(PERSON_QUANTITY_CREATED)));
        assertThat(allPersons.getBody().stream().findFirst().get().getId(), is(equalTo(FIRST_PERSON_ID)));

        verify(personDAO).findAll();
    }

}