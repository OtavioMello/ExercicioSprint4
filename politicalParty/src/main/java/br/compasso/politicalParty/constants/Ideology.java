package br.compasso.politicalParty.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Ideology {

    DIREITA("direita"),
    CENTRO("centro"),
    ESQUERDA("esquerda");

    Ideology(String value){
        this.value = value;
    }

    private String value;

    @JsonCreator
    public static Ideology values(String value){
        for (Ideology ideology: Ideology.values()){
            if(ideology.value.equalsIgnoreCase(value)){
                return ideology;
            }
        }
        return null;
    }
}
