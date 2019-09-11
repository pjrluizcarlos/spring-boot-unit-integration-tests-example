package com.luizcarlospjr.junitmockitounittest.person.impl.api.v1.withmockwithrestinterface;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizcarlospjr.junitmockitounittest.person.api.v1.PersonInputDTO;
import com.luizcarlospjr.junitmockitounittest.person.impl.api.v1.PersonRestController;
import com.luizcarlospjr.junitmockitounittest.person.impl.infrastructure.PersonDAO;
import com.luizcarlospjr.junitmockitounittest.person.impl.infrastructure.PersonEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonRestControllerIntegrationTest {

    private static final Long FIRST_PERSON_ID = 1L;
    private static final Integer PERSON_QUANTITY_CREATED = 1;
    private static final String PERSON_NAME = "Luiz";
    private static final String PERSON_CPF = "02202202222";
    private static final Long PERSON_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Jackson2ObjectMapperBuilder objectMapperBuilder;

    @MockBean
    private PersonDAO personDAO;

    @Test
    public void shouldCreatePerson() throws Exception {
        PersonEntity personEntity = PersonEntity.builder()
                .cpf(PERSON_CPF)
                .name(PERSON_NAME)
                .build();

        PersonEntity personEntityWithId = PersonEntity.builder()
                .id(PERSON_ID)
                .cpf(PERSON_CPF)
                .name(PERSON_NAME)
                .build();

        given(personDAO.save(personEntity)).willReturn(personEntityWithId);
        given(personDAO.findAll()).willReturn(asList(personEntityWithId));

        PersonInputDTO inputDTO = PersonInputDTO.builder()
                .cpf(PERSON_CPF)
                .name(PERSON_NAME)
                .build();

        ObjectMapper objectMapper = objectMapperBuilder.build();
        String content = objectMapper.writeValueAsString(inputDTO);

        mockMvc.perform(post("/api/person/v1/persons")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(equalTo(FIRST_PERSON_ID.intValue()))));

        verify(personDAO).save(personEntity);

        mockMvc.perform(get("/api/person/v1/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(PERSON_QUANTITY_CREATED)))
                .andExpect(jsonPath("$[0].id",is(equalTo(FIRST_PERSON_ID.intValue()))));

        verify(personDAO).findAll();
    }

}