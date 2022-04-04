package br.compasso.politicalParty.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {

    MASCULINO("masculino"),
    FEMININO("feminino");

    Gender(String value){
        this.value = value;
    }

    private String value;

    @JsonCreator
    public static Gender values(String value){
        for (Gender gender: Gender.values()){
            if(gender.value.equalsIgnoreCase(value)){
                return gender;
            }
        }
        return null;
    }
}
