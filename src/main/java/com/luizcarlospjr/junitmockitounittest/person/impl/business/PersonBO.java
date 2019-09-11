package com.luizcarlospjr.junitmockitounittest.person.impl.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PersonBO {

    private Long id;
    private String name;
    private String cpf;

}
