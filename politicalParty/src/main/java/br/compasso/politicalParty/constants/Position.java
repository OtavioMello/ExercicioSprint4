package br.compasso.politicalParty.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Position {

    VEREADOR("vereador"),
    PREFEITO("prefeito"),
    DEPUTADO_ESTADUAL("deputado_estadual"),
    DEPUTADO_FEDERAL("deputado_federal"),
    SENADOR("senador"),
    GOVERNADOR("governador"),
    PRESIDENTE("presidente"),
    NENHUM("nenhum");

    Position(String value){
        this.value = value;
    }

    private String value;

    @JsonCreator
    public static Position values(String value){
        for (Position positon : Position.values()){
            if(positon.value.equalsIgnoreCase(value)){
                return positon;
            }
        }
        return null;
    }
}
