package com.luizcarlospjr.junitmockitounittest.person.impl.infrastructure;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {

    private Long id;
    private String name;
    private String cpf;

}
