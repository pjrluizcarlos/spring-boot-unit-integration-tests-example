package com.luizcarlospjr.junitmockitounittest.person.impl.business;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Component
public class PersonValidator {

    private static final int CPF_CORRECT_LENGTH = 11;

    public void validateCreatePerson(PersonBO bo) {
        Assert.notNull(bo, "PersonBO cannot be null to create person");

        if (StringUtils.isEmpty(bo.getName())) {
            throw new IllegalArgumentException("Persons name cannot be empty to create person");
        }

        if (StringUtils.isEmpty(bo.getCpf())) {
            throw new IllegalArgumentException("Persons CPF cannot be empty to create person");
        }

        if (bo.getCpf().toCharArray().length != CPF_CORRECT_LENGTH) {
            throw new IllegalArgumentException("Persons CPF must have 11 characters size");
        }
    }

    public void validateUpdatePerson(PersonBO bo) {
        Assert.notNull(bo, "PersonBO cannot be null to update person");

        if (StringUtils.isEmpty(bo.getName())) {
            throw new IllegalArgumentException("Persons name cannot be empty to update person");
        }

        if (StringUtils.isEmpty(bo.getCpf())) {
            throw new IllegalArgumentException("Persons CPF cannot be empty to update person");
        }

        if (bo.getCpf().toCharArray().length != CPF_CORRECT_LENGTH) {
            throw new IllegalArgumentException("Persons CPF must have 11 characters size");
        }

        if (bo.getId() == null) {
            throw new IllegalArgumentException("Persons ID cannot be empty to update person");
        }
    }

}
