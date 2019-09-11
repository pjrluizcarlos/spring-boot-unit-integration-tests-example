package com.luizcarlospjr.junitmockitounittest.person.impl.api.v1;

import com.luizcarlospjr.junitmockitounittest.person.api.v1.PersonInputDTO;
import com.luizcarlospjr.junitmockitounittest.person.api.v1.PersonOutputDTO;
import com.luizcarlospjr.junitmockitounittest.person.api.v1.PersonUpdateDTO;
import com.luizcarlospjr.junitmockitounittest.person.impl.business.PersonBO;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class PersonApiConverter {

    public PersonBO toBO(PersonInputDTO dto) {
        Assert.notNull(dto, "PersonInputDTO cannot be null to convert into PersonBO");

        return PersonBO.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .build();
    }

    public PersonOutputDTO toDTO(PersonBO bo) {
        Assert.notNull(bo, "PersonBO cannot be null to convert into PersonOutputDTO");

        return PersonOutputDTO.builder()
                .id(bo.getId())
                .name(bo.getName())
                .cpf(bo.getCpf())
                .build();
    }

    public List<PersonOutputDTO> toDTO(List<PersonBO> bos) {
        return isEmpty(bos) ? emptyList() : bos.stream().map(this::toDTO).collect(toList());
    }

    public PersonBO toBO(Long id, PersonUpdateDTO dto) {
        Assert.notNull(dto, "PersonUpdateDTO cannot be null to convert into PersonBO");

        return PersonBO.builder()
                .id(id)
                .name(dto.getName())
                .cpf(dto.getCpf())
                .build();
    }

}
