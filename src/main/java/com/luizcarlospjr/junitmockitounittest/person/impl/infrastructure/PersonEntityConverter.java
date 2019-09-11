package com.luizcarlospjr.junitmockitounittest.person.impl.infrastructure;

import com.luizcarlospjr.junitmockitounittest.person.impl.business.PersonBO;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class PersonEntityConverter {

    public PersonEntity toEntity(PersonBO bo) {
        Assert.notNull(bo, "PersonBO cannot be null to convert to PersonEntity");

        return PersonEntity.builder()
                .id(bo.getId())
                .name(bo.getName())
                .cpf(bo.getCpf())
                .build();
    }

    public PersonBO toBO(PersonEntity entity) {
        Assert.notNull(entity, "PersonEntity cannot be null to convert to PersonBO");

        return PersonBO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cpf(entity.getCpf())
                .build();
    }

    public List<PersonBO> toBO(List<PersonEntity> all) {
        return isEmpty(all) ? emptyList() : all.stream().map(this::toBO).collect(toList());
    }

}
