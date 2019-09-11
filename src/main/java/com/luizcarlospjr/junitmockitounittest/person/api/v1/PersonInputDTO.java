package com.luizcarlospjr.junitmockitounittest.person.api.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonInputDTO {

    private String name;
    private String cpf;

}
