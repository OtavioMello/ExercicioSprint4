package br.compasso.politicalParty.dto;

import br.compasso.politicalParty.constants.Ideology;
import br.compasso.politicalParty.entities.PoliticalParty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoliticalPartyDTO {

    private Long id;
    private String name;
    private String acronym;
    private Ideology ideology;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date foundationDate;

    public PoliticalPartyDTO(PoliticalParty politicalParty) {
        this.id = politicalParty.getId();
        this.name = politicalParty.getName();
        this.acronym = politicalParty.getAcronym();
        this.ideology = politicalParty.getIdeology();
        this.foundationDate = politicalParty.getFoundationDate();
    }
}
